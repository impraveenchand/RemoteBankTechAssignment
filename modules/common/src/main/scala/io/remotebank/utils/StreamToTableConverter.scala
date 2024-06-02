package io.remotebank.utils

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.table.api.{AnyWithOperations, FieldExpression, Schema, Table}

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

trait StreamToTableConverter {
  def convertToTable[T <: Product](
                                    stream: DataStream[T],
                                    clazz: Class[T],
                                    proctimeAttributeName: Option[String] = None,
                                    aliasPrefix: Option[String] = None,
                                    schema: Option[Schema] = None
                                  )(implicit tEnv: StreamTableEnvironment, sEnv: StreamExecutionEnvironment): Table = {
    val fieldNames = clazz.getDeclaredFields
      .map(_.getName)
      .toList
    convertToTable(stream, fieldNames, proctimeAttributeName, aliasPrefix, schema)
  }

  def convertToTable[T](
                         stream: DataStream[T],
                         fieldNames: List[String],
                         proctimeAttributeName: Option[String],
                         aliasPrefix: Option[String],
                         sc: Option[Schema]
                       )(implicit tEnv: StreamTableEnvironment, sEnv: StreamExecutionEnvironment): Table = {

    val schemaColNames = sc.get.getColumns.map(c => c.getName).toList

    val table = tEnv.fromDataStream(stream, sc.get)

    val renamedTable = schemaColNames.foldLeft(table) { (currentTable, col) =>
      currentTable.renameColumns($"$col".as(s"${aliasPrefix.get}$col"))
    }

    renamedTable.printSchema()
    renamedTable

  }

  def convertToTableWithOutSchema[T](
                                      stream: DataStream[T]
                                    )(implicit tEnv: StreamTableEnvironment, sEnv: StreamExecutionEnvironment): Table = {

    val table = tEnv.fromDataStream(stream)

    table.printSchema()
    table

  }
}