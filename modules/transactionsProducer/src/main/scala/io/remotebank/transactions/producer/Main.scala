package io.remotebank.transactions.producer

import io.remotebank.sink.KafkaAvroSink
import io.remotebank.sink.serializer.TransactionEventSerializer
import io.remotebank.transaction.event.value.TransactionEventValue
import io.remotebank.utils.StreamEnvBuilder
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

class Main {
  self: StreamEnvBuilder =>
  def runMain(args: Array[String]): Unit = {

    val jobParameters = ParameterTool.fromArgs(args)

    // Accessing parameters
    val sinkParallelism = jobParameters.getInt("sinkParallelism", 1)
    val maxIterations = jobParameters.getInt("maxIterations", 5)
    val sinkTopic = jobParameters.get("sinkTopic", "transactions")
    val sinkSubject = jobParameters.get("sinkSubject", "transactions")
    val schemaRegistryUrl = jobParameters.get("schemaRegistryUrl", "http://127.0.0.1:8081")
    val bootstrapServers = jobParameters.get("bootstrapServers", "localhost:9092")
    val transactionTimeoutMillis = jobParameters.getInt("transactionTimeoutMillis", 60000)


    implicit val sEnv: StreamExecutionEnvironment = buildStreamEnv()

    val serializer = new TransactionEventSerializer(
      sinkSubject,
      schemaRegistryUrl,
      sinkTopic
    )

    val resultSink = new KafkaAvroSink[TransactionEventValue](
      bootstrapServers,
      serializer,
      transactionTimeoutMillis,
      sinkParallelism
    )

    val transactionJob = new TransactionAvroEventsGenerator(
      resultSink,
      maxIterations
    )
    transactionJob.buildStreamGraph()
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val main = new Main() with JobStreamEnvBuilder
    main.runMain(args)
  }
}

