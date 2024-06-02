package io.remotebank.sink

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}


trait Sink[T] {
  def addSink(stream: DataStream[T])(implicit sEnv: StreamExecutionEnvironment): Unit

  def name: String
}
