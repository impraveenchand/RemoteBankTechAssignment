package io.remotebank.sink.serializer

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.avro.specific.SpecificRecord
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema

import scala.collection.JavaConverters._

trait KeyedKafkaAvroSerializer[T <: SpecificRecord] extends KafkaRecordSerializationSchema[T] {

  @transient protected lazy val keySerializer: KafkaAvroSerializer = {
    val schemaRegistryClient = new CachedSchemaRegistryClient(schemaRegistryUrl, 8)
    val config = Map(
      "schema.registry.url" -> schemaRegistryUrl,
      "auto.register.schemas" -> "false"
    ).asJava
    val serializer = new KafkaAvroSerializer(schemaRegistryClient)
    serializer.configure(config, true)
    serializer
  }

  @transient protected lazy val valueSerializer: KafkaAvroSerializer = {
    val schemaRegistryClient = new CachedSchemaRegistryClient(schemaRegistryUrl, 8)
    val config = Map(
      "schema.registry.url" -> schemaRegistryUrl,
      "auto.register.schemas" -> "false"
    ).asJava

    val serializer = new KafkaAvroSerializer(schemaRegistryClient)
    serializer.configure(config, false)
    serializer
  }

  def schemaRegistryUrl: String

  def topic: String

  def subject: String

}

