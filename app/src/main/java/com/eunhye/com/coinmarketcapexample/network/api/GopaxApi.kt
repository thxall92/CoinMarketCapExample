package com.eunhye.com.coinmarketcapexample.network.api

import com.eunhye.com.coinmarketcapexample.network.model.GopaxTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GopaxApi {

    @GET("trading-pairs/stats")
    fun getTickers(): Single<List<GopaxTickerResponse>>

    @GET("/trading-pairs/{symbol}/stats")
    fun getTicker(@Path("symbol") symbol: String): Single<GopaxTickerResponse>

}