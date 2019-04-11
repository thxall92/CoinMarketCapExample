package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.viewmodel.CoinListViewModel
import com.eunhye.com.coinmarketcapexample.viewmodel.ExchangeSelectViewModel
import com.eunhye.com.coinmarketcapexample.viewmodel.WelcomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { WelcomeViewModel() }
    viewModel { CoinListViewModel(get()) }
    viewModel { ExchangeSelectViewModel(androidApplication(), get())}
}