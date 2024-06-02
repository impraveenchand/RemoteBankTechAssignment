package io.remotebank.utils

import io.remotebank.sink.Sink
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

// scalastyle:off
object implicits {
  implicit class DataStreamExt[T](stream: DataStream[T]) {
    def addSink(sink: Sink[T])(implicit sEnv: StreamExecutionEnvironment): Unit =
      sink.addSink(stream)
  }
}
// scalastyle:on

