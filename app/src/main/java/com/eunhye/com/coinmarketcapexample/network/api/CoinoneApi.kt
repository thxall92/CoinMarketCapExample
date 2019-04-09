package com.eunhye.com.coinmarketcapexample.network.api

import com.eunhye.com.coinmarketcapexample.network.model.CoinoneAllTicker
import io.reactivex.Single
import retrofit2.http.GET

interface CoinoneApi {

    @GET(value = "ticker/?currency=all")
    fun allTicker(): Single<CoinoneAllTicker>
}