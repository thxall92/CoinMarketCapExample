package com.eunhye.com.coinmarketcapexample.data.model

import com.eunhye.com.coinmarketcapexample.network.model.ExchangeTicker

open class Ticker(
    var currency: String? = "",
    var baseCurrency: String? = "",
    var last: Double?,
    var high: Double?,
    var low: Double?,
    var volume: Double?,
    var diff: Double? = null) : ITicker {

    override fun toTicker() = this

    override fun toExchangeTicker(exchange: String) = ExchangeTicker(exchange, this)
}