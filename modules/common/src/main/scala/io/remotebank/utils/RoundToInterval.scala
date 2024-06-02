package io.remotebank.utils

import java.time.{Instant, ZoneOffset, ZonedDateTime}

class RoundToInterval(intervalMins: Int) extends RichScalarFunction {
  def eval(eventTime: Long): Long = {
    // TODO: Add metrics
    val dt = Instant.ofEpochMilli(eventTime)
    val zdt = ZonedDateTime.ofInstant(dt, ZoneOffset.UTC)
    val minuteRoundOff = (zdt.getMinute / intervalMins) * intervalMins
    val rounded = zdt
      .withMinute(minuteRoundOff)
      .withSecond(0)
      .toEpochSecond

    val roundedMillis = rounded * 1000
    roundedMillis
  }

}

