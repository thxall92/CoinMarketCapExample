package com.eunhye.com.coinmarketcapexample.data.source

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange

interface MainExchangeDataSource {
    fun getSelectedExchange(): Exchange?
    fun getTickerDataSource(): TickerDataSource
}