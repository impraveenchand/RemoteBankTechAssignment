package io.remotebank.utils

import org.apache.flink.table.functions.ScalarFunction


class ToMillis extends ScalarFunction {
  def eval(ts: java.sql.Timestamp): Long = ts.getTime
}
