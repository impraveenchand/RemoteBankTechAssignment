package io.remotebank.utils

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

trait StreamEnvBuilder {
  def buildStreamEnv(): StreamExecutionEnvironment
}
