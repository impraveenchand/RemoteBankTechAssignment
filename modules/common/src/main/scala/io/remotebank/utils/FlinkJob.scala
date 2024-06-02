package io.remotebank.utils

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

trait FlinkJob {
  def buildStreamGraph()(implicit sEnv: StreamExecutionEnvironment): Unit
}
