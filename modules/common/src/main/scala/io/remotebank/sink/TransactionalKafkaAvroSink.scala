package io.remotebank.sink

import io.remotebank.sink.serializer.KeyedKafkaAvroSerializer
import org.apache.avro.specific.SpecificRecord
import org.apache.flink.connector.base.DeliveryGuarantee
import org.apache.flink.connector.kafka.sink.KafkaSink
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.kafka.clients.producer.ProducerConfig

import java.util.{Properties, UUID}

class TransactionalKafkaAvroSink[T <: SpecificRecord](
                                                       bootstrapServers: String,
                                                       serializer: KeyedKafkaAvroSerializer[T],
                                                       transactionTimeoutMillis: Long,
                                                       parallelism: Integer = 1,
                                                     ) extends Sink[T] {

  override val name: String = serializer.topic

  override def addSink(stream: DataStream[T])(implicit sEnv: StreamExecutionEnvironment): Unit = {

    val props = new Properties
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    props.setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, transactionTimeoutMillis.toString)
    props.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "120000")

    val kSink: KafkaSink[T] = KafkaSink
      .builder()
      .setKafkaProducerConfig(props)
      .setRecordSerializer(serializer)
      .setDeliveryGuarantee(DeliveryGuarantee.EXACTLY_ONCE)
      .setTransactionalIdPrefix("kafka-broker-" + UUID.randomUUID())
      .build()

    stream
      .sinkTo(kSink)
      .setParallelism(parallelism)
      .name(s"KafkaProducer[topic=$name]")

  }
}

