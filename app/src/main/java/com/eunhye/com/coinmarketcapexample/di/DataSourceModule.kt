package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.source.MainExchangeDataSource
import com.eunhye.com.coinmarketcapexample.data.source.MainExchangeRepository
import com.eunhye.com.coinmarketcapexample.data.source.TickerDataSource
import com.eunhye.com.coinmarketcapexample.data.source.TickerRepository
import org.koin.dsl.module.module

const val COINONE_TICKER_DATA_SOURCE = "COINONE_TICKER_DATA_SOURCE"

val dataSourceModule = module {
    single(COINONE_TICKER_DATA_SOURCE) { TickerRepository(get()) as TickerDataSource }
    single {
        MainExchangeRepository(get(),
            mapOf(Exchange.COINONE.name to get(COINONE_TICKER_DATA_SOURCE))
        ) as MainExchangeDataSource
    }
}