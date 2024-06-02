package io.remotebank.sink

import io.remotebank.transaction.aggregated.event.value.TransactionAggregatedEventValue
import org.apache.flink.api.connector.sink2.SinkWriter
import org.apache.flink.connector.elasticsearch.sink.{Elasticsearch6SinkBuilder, RequestIndexer}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests

import scala.collection.JavaConverters.mapAsJavaMap

class ElasticSearchSink extends Sink[TransactionAggregatedEventValue] {

  override def addSink(stream: DataStream[TransactionAggregatedEventValue])(implicit sEnv: StreamExecutionEnvironment): Unit = {

    def createIndexRequest(element: TransactionAggregatedEventValue): IndexRequest = {

      val json = Map(
        "user_id" -> element.getUserId.toInt,
        "total_transactions_count" -> element.getTotalTransactionsCount.toInt
      )

      Requests.indexRequest.index("transaction-aggregated-events").`type`("transactions")
        .source(mapAsJavaMap(json))
    }

    val elasticsearch6SinkBuilder = new Elasticsearch6SinkBuilder[TransactionAggregatedEventValue]
      .setBulkFlushInterval(30000)
      .setHosts(new HttpHost("localhost", 9200, "http"))
      .setEmitter(
        (element: TransactionAggregatedEventValue, context: SinkWriter.Context,
         indexer: RequestIndexer) =>
          indexer.add(createIndexRequest(element)))
      .build()

    stream.sinkTo(elasticsearch6SinkBuilder)
  }

  override def name: String = "someName"
}
