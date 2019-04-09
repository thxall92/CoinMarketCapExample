package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.viewmodel.CoinListViewModel
import com.eunhye.com.coinmarketcapexample.viewmodel.WelcomeViewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {
    bean { WelcomeViewModel() }
    bean { CoinListViewModel(get()) }
}