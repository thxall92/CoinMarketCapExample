package com.eunhye.com.coinmarketcapexample.data.source

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.source.ticker.TickerDataSource
import com.eunhye.com.coinmarketcapexample.util.PrefUtils

class MainExchangeRepository(private val prefUtils: PrefUtils,
                             private val tickerDataSourceMap: Map<String, TickerDataSource>)
    : MainExchangeDataSource {

    override fun saveMainExchange(exchange: Exchange) {
        prefUtils.saveExchange(exchange)
    }

    override fun getSelectedExchange() = prefUtils.getExchange()

    override fun getTickerDataSource() = tickerDataSourceMap[getSelectedExchange()!!.name]!!
}