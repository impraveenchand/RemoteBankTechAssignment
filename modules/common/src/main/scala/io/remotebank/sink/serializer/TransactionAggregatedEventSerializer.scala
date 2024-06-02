package io.remotebank.sink.serializer

import io.remotebank.transaction.aggregated.event.key.TransactionAggregatedEventKey
import io.remotebank.transaction.aggregated.event.value.TransactionAggregatedEventValue
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema
import org.apache.kafka.clients.producer.ProducerRecord

import java.lang

class TransactionAggregatedEventSerializer(
                                            override val subject: String,
                                            override val schemaRegistryUrl: String,
                                            override val topic: String
                                          ) extends KeyedKafkaAvroSerializer[TransactionAggregatedEventValue] {
  override def serialize(
                          element: TransactionAggregatedEventValue,
                          context: KafkaRecordSerializationSchema.KafkaSinkContext,
                          timestamp: lang.Long
                        ): ProducerRecord[Array[Byte], Array[Byte]] = {

    val transactionAggregatedEventKey = new TransactionAggregatedEventKey(element.getUserId)
    val key: Array[Byte] = keySerializer.serialize(subject, transactionAggregatedEventKey)
    val value: Array[Byte] = valueSerializer.serialize(subject, element)

    new ProducerRecord(topic, key, value)
  }
}

