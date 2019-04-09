package com.eunhye.com.coinmarketcapexample

import android.app.Application
import com.eunhye.com.coinmarketcapexample.di.dataSourceModule
import com.eunhye.com.coinmarketcapexample.di.dbModule
import com.eunhye.com.coinmarketcapexample.di.networkModule
import com.eunhye.com.coinmarketcapexample.di.viewModelModule
import org.koin.standalone.StandAloneContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            listOf(
                dbModule,
                networkModule,
                dataSourceModule,
                viewModelModule
            )
        )
    }
}
