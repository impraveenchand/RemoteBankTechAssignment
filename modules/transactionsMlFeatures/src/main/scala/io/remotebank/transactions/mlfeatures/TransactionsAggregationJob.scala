package io.remotebank.transactions.mlfeatures

import io.remotebank.sink.Sink
import io.remotebank.source.Source
import io.remotebank.transaction.aggregated.event.value.TransactionAggregatedEventValue
import io.remotebank.transaction.event.value.TransactionEventValue
import io.remotebank.utils.FlinkJob
import io.remotebank.utils.implicits._
import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

class TransactionsAggregationJob(
                                  enrichedSource: Source[TransactionEventValue],
                                  aggregationSink: Sink[TransactionAggregatedEventValue],
                                  elasticSearchSink: Sink[TransactionAggregatedEventValue]
                                ) extends FlinkJob {
  override def buildStreamGraph()(implicit sEnv: StreamExecutionEnvironment): Unit = {

    implicit val tEnv: StreamTableEnvironment = StreamTableEnvironment.create(sEnv)

    val rawStream = enrichedSource
      .buildDataStream()

    val (aggregated, fieldsToType) = new TransactionAggregationTableConverter()
      .aggregate(rawStream)

    val result = aggregated
      .flatMap(new TransactionsAggregatedRowToAvroMapper(fieldsToType))

    println("The aggregation events with output topic schema --->")
    result.print()

//    result.addSink(aggregationSink)
    result.addSink(elasticSearchSink)

  }
}
