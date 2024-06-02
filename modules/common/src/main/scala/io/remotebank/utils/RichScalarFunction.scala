package io.remotebank.utils

import org.apache.flink.table.functions.{FunctionContext, ScalarFunction}

trait RichScalarFunction extends ScalarFunction {
  protected var _context: FunctionContext = _

  def context(): FunctionContext = _context

  override def open(context: FunctionContext): Unit = {
    super.open(context)
    this._context = context
  }
}
