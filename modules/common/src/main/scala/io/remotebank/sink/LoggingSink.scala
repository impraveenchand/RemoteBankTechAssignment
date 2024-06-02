package io.remotebank.sink

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.slf4j.LoggerFactory

class LoggingSink[T](override val name: String, debug: Boolean = false) extends Sink[T] with Serializable {
  override def addSink(stream: DataStream[T])(implicit sEnv: StreamExecutionEnvironment): Unit = {
    stream
      .addSink { e =>
        val logger = LoggerFactory.getLogger(this.getClass)
        val log = s"$name received event=[$e]"

        if (debug) {
          logger.debug(log)
        } else {
          logger.info(log)
        }
      }
      .name(s"Logger[name=$name]")
  }
}
