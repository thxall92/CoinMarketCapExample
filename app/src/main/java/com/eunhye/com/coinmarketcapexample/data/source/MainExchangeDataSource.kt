package com.eunhye.com.coinmarketcapexample.data.source

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.source.ticker.TickerDataSource

interface MainExchangeDataSource {

    fun saveMainExchange(exchange: Exchange)

    fun getSelectedExchange(): Exchange?

    fun getTickerDataSource(): TickerDataSource
}