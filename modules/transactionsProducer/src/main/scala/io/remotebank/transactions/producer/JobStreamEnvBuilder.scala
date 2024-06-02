package io.remotebank.transactions.producer

import io.remotebank.utils.StreamEnvBuilder
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}

trait JobStreamEnvBuilder extends StreamEnvBuilder {
  def buildStreamEnv(): StreamExecutionEnvironment = {
    val sEnv = StreamExecutionEnvironment.getExecutionEnvironment

    sEnv.disableOperatorChaining()

    val stateBackend: HashMapStateBackend = new HashMapStateBackend()

    sEnv.setStateBackend(stateBackend)
    sEnv.setParallelism(1)
    sEnv.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    sEnv.getConfig.enableObjectReuse()

    sEnv
  }
}
