package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.viewmodel.CoinListViewModel
import com.eunhye.com.coinmarketcapexample.viewmodel.ExchangeSelectViewModel
import com.eunhye.com.coinmarketcapexample.viewmodel.WelcomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val viewModelModule = module {
    single { WelcomeViewModel() }
    single { CoinListViewModel(get()) }
    single { ExchangeSelectViewModel(androidApplication())}
}