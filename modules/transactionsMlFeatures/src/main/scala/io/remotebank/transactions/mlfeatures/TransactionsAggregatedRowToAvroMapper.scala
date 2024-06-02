package io.remotebank.transactions.mlfeatures


import io.remotebank.transaction.aggregated.event.value.TransactionAggregatedEventValue
import io.remotebank.utils.FlinkRowToAvroMapper
import org.apache.flink.api.common.typeinfo.TypeInformation

class TransactionsAggregatedRowToAvroMapper(
                                             override val rtiMap: Seq[(String, TypeInformation[_])],
                                             override val clazz: Class[TransactionAggregatedEventValue] = classOf[TransactionAggregatedEventValue]
                                           ) extends FlinkRowToAvroMapper[TransactionAggregatedEventValue]
