package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.data.source.TickerDataSource
import com.eunhye.com.coinmarketcapexample.data.source.TickerRepository
import org.koin.dsl.module.applicationContext

val dataSourceModule = applicationContext {
    bean { TickerRepository(get()) as TickerDataSource}
}