package io.remotebank.transactions.producer

import io.remotebank.sink.Sink
import io.remotebank.transaction.event.value.TransactionEventValue
import io.remotebank.utils.FlinkJob
import io.remotebank.utils.implicits._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}

import java.util.Random

class TransactionAvroEventsGenerator(
                                      TransactionSink: Sink[TransactionEventValue],
                                      maxIterations: Int
                                    ) extends FlinkJob {

  override def buildStreamGraph()
                               (implicit sEnv: StreamExecutionEnvironment): Unit = {

    var iteration = 0

    while (iteration < maxIterations) {
      val dummyAvroData: DataStream[TransactionEventValue] = sEnv
        .fromCollection(
          (1 to 20).map(_ => createSpecificRecord())
        )

      println(s"The iteration number: $iteration")
      dummyAvroData.print()
      dummyAvroData.addSink(TransactionSink)
      sEnv.execute(s"ContinuousAvroDataToKafka-execution-$iteration")
      iteration += 1
    }
  }

  private def createSpecificRecord(): TransactionEventValue = {
    val record = new TransactionEventValue()

    val random = new Random()
    // Define an array of specific string values
    val currencyValues = Array("INR", "USD")

    // Generate a random index to select a value from the array
    val randomIndex = random.nextInt(currencyValues.length)

    // Select a value using the random index
    val randomCurrency = currencyValues(randomIndex)
    val randomUserId = 1 + random.nextInt(10)
    val randomCounterPartId = 100000 + random.nextInt(800000)
    val randomTransactionTimeMillis = 1705996320000L + random.nextInt(86400000)
    val randomAmount = 10 + random.nextInt(1000)

    record.setUserId(randomUserId)
    record.setAmount(randomAmount)
    record.setTransactionTimestampMillis(randomTransactionTimeMillis)
    record.setCurrency(randomCurrency)
    record.setCounterpartId(randomCounterPartId)
    record
  }
}

