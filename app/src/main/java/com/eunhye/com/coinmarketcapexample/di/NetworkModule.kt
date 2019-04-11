package com.eunhye.com.coinmarketcapexample.di

import com.eunhye.com.coinmarketcapexample.BuildConfig
import com.eunhye.com.coinmarketcapexample.network.api.CoinoneApi
import com.eunhye.com.coinmarketcapexample.network.api.GopaxApi
import com.eunhye.com.coinmarketcapexample.network.api.UpbitApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.CoinoneRestUrl)
            .client(get())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(CoinoneApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.UpbitRestUrl)
            .client(get())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(UpbitApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.GopaxRestUrl)
            .client(get())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
            .create(GopaxApi::class.java)
    }
}