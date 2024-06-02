package io.remotebank.transactions.mlfeatures

import io.remotebank.utils.StreamEnvBuilder
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}

trait JobStreamEnvBuilder extends StreamEnvBuilder {
  def buildStreamEnv(): StreamExecutionEnvironment = {
    val sEnv = StreamExecutionEnvironment.getExecutionEnvironment

    sEnv.disableOperatorChaining()

    sEnv.enableCheckpointing(30000)
    sEnv.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    sEnv.getCheckpointConfig.setMinPauseBetweenCheckpoints(500)
    sEnv.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    sEnv.getCheckpointConfig.setCheckpointTimeout(5 * 60 * 1000)

    val stateBackend: HashMapStateBackend = new HashMapStateBackend()

    sEnv.setStateBackend(stateBackend)
    sEnv.getCheckpointConfig.setCheckpointStorage("file:///D:/flinkCheckpoint/transactions-ml-features/")

    sEnv.setParallelism(1)
    sEnv.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    sEnv.getConfig.enableObjectReuse()

    sEnv
  }
}
