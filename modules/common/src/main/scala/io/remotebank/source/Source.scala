package io.remotebank.source

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

trait Source[T] {
  def buildDataStream()(implicit sEnv: StreamExecutionEnvironment): DataStream[T]
}

