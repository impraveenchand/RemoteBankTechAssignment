package io.remotebank.utils

import org.apache.avro.Schema
import org.apache.avro.specific.SpecificRecordBase
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.types.Row
import org.apache.flink.util.Collector

import scala.collection.JavaConverters._
import scala.util.Try

// due to flink row can't accept NULL field when transforming
// to case class, a similar error is:
// https://stackoverflow.com/questions/58348739/
// nullpointerexception-exception-when-using-flinks-leftouterjoinlateral-in-scala

trait FlinkRowToAvroMapper[T <: SpecificRecordBase]
  extends RichFlatMapFunction[Row, T] {

  def rtiMap: Seq[(String, TypeInformation[_])]

  def clazz: Class[T]

  @transient lazy val targetAvroSchema: Schema = clazz.getConstructor().newInstance().getSchema
  @transient lazy val targetAvroSchemaFields: Set[String] = targetAvroSchema.getFields.asScala.map(_.name()).toSet

  override def flatMap(value: Row, out: Collector[T]): Unit = {

    val outRecord = clazz.getConstructor().newInstance()

    val fieldNames = rtiMap.map(_._1)
    var erroneousRecord: Boolean = false

    val rtiToMap = rtiMap.toMap
    rtiToMap.keys.foreach(
      fieldName =>
        Try {

          val rowValue = value.getField(fieldNames.indexOf(fieldName))
          if (rowValue != null && targetAvroSchemaFields.contains(fieldName)) {

            val avroType = targetAvroSchema.getField(fieldName)

            val fieldValueTyped = CommonUtils.mapRowToAvroTypes(avroType, rowValue) match {
              case null => null
              case error if error.toString.contentEquals("error") => erroneousRecord = true; error
              case avro => avro
            }
            if (fieldValueTyped != null) {
              val field = outRecord.getClass.getDeclaredField(fieldName)
              field.setAccessible(true)
              field.set(outRecord, fieldValueTyped)
            }

          }

        } match {
          case scala.util.Success(value) => value
          case scala.util.Failure(exception) =>
            throw exception
        }
    )

    if (!erroneousRecord) {
      out.collect(outRecord)
    }

  }

}

