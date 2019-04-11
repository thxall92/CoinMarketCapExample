package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.util.PrefUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dbModule = module {
    single { PrefUtils(androidApplication()) }
}