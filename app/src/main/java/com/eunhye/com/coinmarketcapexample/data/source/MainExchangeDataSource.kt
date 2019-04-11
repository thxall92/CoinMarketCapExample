package com.eunhye.com.coinmarketcapexample.data.source

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange

interface MainExchangeDataSource {

    fun saveMainExchange(exchange: Exchange)

    fun getSelectedExchange(): Exchange?

    fun getTickerDataSource(): TickerDataSource

}