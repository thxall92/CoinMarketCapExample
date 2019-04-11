package com.eunhye.com.coinmarketcapexample.network.api

import io.reactivex.Single
import retrofit2.http.GET

interface CoinoneApi {

    @GET("ticker/?currency=all")
    fun getAllTicker(): Single<Map<String, Any>>
}