package com.eunhye.com.coinmarketcapexample.data.model

import com.eunhye.com.coinmarketcapexample.network.model.ExchangeTicker

interface ITicker {
    fun toTicker(): Ticker
    fun toExchangeTicker(exchange: String = "empty"): ExchangeTicker
}