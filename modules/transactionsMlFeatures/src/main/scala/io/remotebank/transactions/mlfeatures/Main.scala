package io.remotebank.transactions.mlfeatures


import io.remotebank.sink.serializer.TransactionAggregatedEventSerializer
import io.remotebank.sink.{ElasticSearchSink, TransactionalKafkaAvroSink}
import io.remotebank.source.KafkaAvroSource
import io.remotebank.transaction.aggregated.event.value.TransactionAggregatedEventValue
import io.remotebank.transaction.event.value.TransactionEventValue
import io.remotebank.utils.StreamEnvBuilder
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.scala._

class Main {
  self: StreamEnvBuilder =>
  def runMain(args: Array[String]): Unit = {

    val jobParameters = ParameterTool.fromArgs(args)

    // Accessing parameters
    val sourceTopic = jobParameters.get("sourceTopic", "transactions")
    val sinkTopic = jobParameters.get("sinkTopic", "transactions-aggregation")
    val sinkSubject = jobParameters.get("sinkSubject", "transactions-aggregation")
    val schemaRegistryUrl = jobParameters.get("schemaRegistryUrl", "http://127.0.0.1:8081")
    val bootstrapServers = jobParameters.get("bootstrapServers", "localhost:9092")
    val transactionTimeoutMillis = jobParameters.getInt("transactionTimeoutMillis", 900000)
    val consumerGroupId = jobParameters.get("consumerGroupId", "transactions-ml-features-job")
    val jobName = jobParameters.get("jobName", "transactions-ml-features-job")

    implicit val sEnv: StreamExecutionEnvironment = buildStreamEnv()

    val SourceEvent = new KafkaAvroSource[TransactionEventValue](
      schemaRegistryUrl,
      bootstrapServers,
      sourceTopic,
      consumerGroupId,
      classOf[TransactionEventValue],
      OffsetsInitializer.earliest()
    )

    val aggregationSerializer = new TransactionAggregatedEventSerializer(
      sinkSubject,
      schemaRegistryUrl,
      sinkTopic
    )

    val aggregationResultSink = new TransactionalKafkaAvroSink[TransactionAggregatedEventValue](
      bootstrapServers,
      aggregationSerializer,
      transactionTimeoutMillis
    )

    val elasticSearchSink = new ElasticSearchSink

    val aggregationJob = new TransactionsAggregationJob(
      SourceEvent,
      aggregationResultSink,
      elasticSearchSink
    )
    aggregationJob.buildStreamGraph()

    sEnv.execute(jobName)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val main = new Main() with JobStreamEnvBuilder
    main.runMain(args)
  }
}

