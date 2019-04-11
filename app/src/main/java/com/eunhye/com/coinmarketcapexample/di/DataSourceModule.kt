package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.source.*
import com.eunhye.com.coinmarketcapexample.data.source.ticker.*
import org.koin.dsl.module.module

const val COINONE_TICKER_DATA_SOURCE = "COINONE_TICKER_DATA_SOURCE"
const val UPBIT_TICKER_DATA_SOURCE = "UPBIT_TICKER_DATA_SOURCE"
const val GOPAX_TICKER_DATA_SOURCE = "GOPAX_TICKER_DATA_SOURCE"

val dataSourceModule = module {
    single(COINONE_TICKER_DATA_SOURCE) {
        CoinoneTickerRepository(get()) as TickerDataSource
    }
    single(UPBIT_TICKER_DATA_SOURCE) {
        UpbitTickerRepository(get()) as TickerDataSource
    }
    single(GOPAX_TICKER_DATA_SOURCE) {
        GopaxTickerRepository(get()) as TickerDataSource
    }
    single {
        MainExchangeRepository(get(),
            mapOf(Exchange.COINONE.name to get(COINONE_TICKER_DATA_SOURCE),
                Exchange.UPBIT.name to get(UPBIT_TICKER_DATA_SOURCE),
                Exchange.GOPAX.name to get(GOPAX_TICKER_DATA_SOURCE))
        ) as MainExchangeDataSource
    }
}