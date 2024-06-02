package io.remotebank.sink.serializer

import io.remotebank.transaction.event.key.TransactionEventKey
import io.remotebank.transaction.event.value.TransactionEventValue
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema
import org.apache.kafka.clients.producer.ProducerRecord

import java.lang

class TransactionEventSerializer(
                                  override val subject: String,
                                  override val schemaRegistryUrl: String,
                                  override val topic: String
                                ) extends KeyedKafkaAvroSerializer[TransactionEventValue] {
  override def serialize(
                          element: TransactionEventValue,
                          context: KafkaRecordSerializationSchema.KafkaSinkContext,
                          timestamp: lang.Long
                        ): ProducerRecord[Array[Byte], Array[Byte]] = {

    val transactionEventKey = new TransactionEventKey(element.getUserId)
    val key: Array[Byte] = keySerializer.serialize(subject, transactionEventKey)
    val value: Array[Byte] = valueSerializer.serialize(subject, element)

    new ProducerRecord(topic, key, value)
  }
}
