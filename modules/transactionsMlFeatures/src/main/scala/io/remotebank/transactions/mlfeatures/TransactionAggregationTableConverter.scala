package io.remotebank.transactions.mlfeatures


import io.remotebank.transaction.event.value.TransactionEventValue
import io.remotebank.utils.{RoundToInterval, StreamToTableConverter, ToMillis}
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.types.Row

class TransactionAggregationTableConverter extends StreamToTableConverter {
  def aggregate(stream: DataStream[TransactionEventValue])(
    implicit sEnv: StreamExecutionEnvironment,
    tEnv: StreamTableEnvironment
  ): (DataStream[Row], Array[(String, TypeInformation[_])]) = {
    tEnv.createTemporarySystemFunction("round_to_interval", new RoundToInterval(30))
    tEnv.createTemporarySystemFunction("to_millis", new ToMillis)

    val streamTable = convertToTableWithOutSchema(stream)

    tEnv.createTemporaryView("TransactionEventTable", streamTable)

    val resultTable = tEnv.sqlQuery(
      """
        |SELECT
        |  user_id,
        |  to_millis(TUMBLE_START(PROCTIME(), INTERVAL '30' SECOND)) AS windowStartTime,
        |  to_millis(TUMBLE_END(PROCTIME(), INTERVAL '30' SECOND)) AS windowEndTime,
        |  CAST(COUNT(counterpart_id) AS INT) AS total_transactions_count
        |FROM TransactionEventTable
        |GROUP BY
        |  TUMBLE(PROCTIME(), INTERVAL '30' SECOND),
        |  user_id
        |""".stripMargin)

    val fieldToTypeMap: Array[(String, TypeInformation[_])] =
      resultTable.getSchema.getFieldNames.zip(resultTable.getSchema.getFieldTypes)

    (tEnv.toDataStream(resultTable, classOf[Row]), fieldToTypeMap)
  }
}
