package io.remotebank.source

import org.apache.avro.specific.SpecificRecord
import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroDeserializationSchema
import org.apache.flink.streaming.api.scala._
import org.apache.kafka.clients.consumer.{ConsumerConfig, OffsetResetStrategy}

import java.util.Properties

/**
 * The avro subject is derived from the topic.
 */
class KafkaAvroSource[T <: SpecificRecord : TypeInformation](
                                                              schemaRegistryUrl: String,
                                                              bootstrapServers: String,
                                                              topic: String,
                                                              consumerGroupId: String,
                                                              clazz: Class[T],
                                                              offsetsInitializer: OffsetsInitializer = OffsetsInitializer
                                                                .committedOffsets(OffsetResetStrategy.EARLIEST),
                                                            ) extends Source[T] {
  override def buildDataStream()(implicit sEnv: StreamExecutionEnvironment): DataStream[T] = {
    val properties = new Properties()
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId)
    properties.setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed")
    val deserializer = ConfluentRegistryAvroDeserializationSchema.forSpecific(clazz, schemaRegistryUrl)
    val kConsumer = buildFlinkKafkaConsumer(topic, deserializer, properties, offsetsInitializer)
    kConsumer
  }

  private def buildFlinkKafkaConsumer(
                                       topic: String,
                                       deserializer: DeserializationSchema[T],
                                       properties: Properties,
                                       offsetsInitializer: OffsetsInitializer
                                     )(implicit sEnv: StreamExecutionEnvironment): DataStream[T] = {
    val kafkaConsumer = KafkaSource
      .builder()
      .setTopics(topic)
      .setValueOnlyDeserializer(deserializer)
      .setProperties(properties)
      .setStartingOffsets(offsetsInitializer)
      .build()

    sEnv.fromSource(kafkaConsumer, WatermarkStrategy.noWatermarks(), s"KafkaConsumer[topic=$topic]")
  }
}

