package io.remotebank.utils

import org.apache.avro.Schema
import org.apache.avro.Schema.Type
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

object CommonUtils {

  @transient lazy val log: Logger = LoggerFactory.getLogger(this.getClass.toString)

  def mapRowToAvroTypes(avroType: Schema.Field, value: AnyRef): Any =
    Try {
      avroType.schema().getType match {
        case Type.STRING => value.toString
        case Type.INT => value.toString.toInt
        case Type.LONG => value.toString.toLong
        case Type.DOUBLE => value.toString.toDouble
        case Type.UNION => mapRowToAvroTypes(getInnerType(avroType), value, avroType.name())
        case Type.BOOLEAN => value.toString.toBoolean
        case _ =>
          throw new NullPointerException(s"Unhandled Type ${avroType} fieldName ${avroType.name()} for value ${value}")
      }
    } match {
      case scala.util.Success(value) => value
      case scala.util.Failure(exception) =>
        log.info(s"Error: field name was ${avroType.name()} value was ${value.toString}", exception); "error"
    }

  def getInnerType(schema: Schema.Field): Type =
    Try {
      val fieldSchema = schema
        .schema()
        .getTypes
        .asScala
        .filterNot(p => p.getType.getName.equalsIgnoreCase("null"))
        .head
        .getName

      Constants.AVRO_STRING_TO_TYPE(fieldSchema.replace("\"", ""))
    } match {
      case scala.util.Success(value) => value
      case scala.util.Failure(exception) => throw exception

    }

  private def mapRowToAvroTypes(avroType: Type, value: AnyRef, name: String): Any =
  //    log.info(value+"_"+avroType.getName)
    try {
      avroType match {
        case Type.STRING => value.toString
        case Type.INT => value.toString.toInt
        case Type.LONG => value.toString.toLong
        case Type.DOUBLE => value.toString.toDouble
        case Type.BOOLEAN => value.toString.toBoolean
        case _ => throw new UnsupportedOperationException(s"data type ${avroType.getName} not supported")
      }
    } catch {
      case e: Exception =>
        log.error(
          s"Unhandled Type ${avroType.getName} with field name fieldName $name for value ${value}"
        );
        null
    }

  def renameFlinkSQLKeywords(field: String): String = {
    var newValue = field
    if (Constants.TableConstant.FLINK_SQL_RESERVED_KEYWORDS.contains(field.toUpperCase)) {
      newValue = s"`$field`"
    }
    newValue
  }

  def createCompositeKeyExpr(cols: List[String], exprReplacement: (String, String)*): String = {
    val compositeKeyExpr: mutable.LinkedHashSet[String] =
      mutable.LinkedHashSet(cols.map(col => renameFlinkSQLKeywords(col)): _*)

    exprReplacement.foreach {
      case (col, toReplace) =>
        compositeKeyExpr.add(toReplace)
        compositeKeyExpr.remove(col)
    }
    s"CONCAT_WS('-',${compositeKeyExpr.mkString(",")}) as adtechEventUUID"
  }

  def extractCategoryIDFromReason(reason: String): Long = {
    reason match {
      case s if s.startsWith("MS|") =>
        val catID = s.split(":")(0).split("\\|")(1)

        // Try to parse catID as a number
        val catIDAsNumber: Long = Try(catID.toLong) match {
          case Success(value) => value
          case Failure(_) => s.split(":")(1).toLong
        }
        catIDAsNumber
      case _ => 404L
    }
  }

}

