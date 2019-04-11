package com.eunhye.com.coinmarketcapexample

import android.app.Application
import com.eunhye.com.coinmarketcapexample.di.dataSourceModule
import com.eunhye.com.coinmarketcapexample.di.dbModule
import com.eunhye.com.coinmarketcapexample.di.networkModule
import com.eunhye.com.coinmarketcapexample.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                networkModule,
                dataSourceModule,
                viewModelModule,
                dbModule
            )
        )
    }
}
