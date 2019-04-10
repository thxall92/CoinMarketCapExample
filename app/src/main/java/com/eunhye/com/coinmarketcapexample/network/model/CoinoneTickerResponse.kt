package com.eunhye.com.coinmarketcapexample.network.model

import com.eunhye.com.coinmarketcapexample.data.model.ITicker
import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import com.google.gson.annotations.SerializedName

const val COINONE_TICKER_FIELD_ERROR_CODE = "errorCode"
const val COINONE_TICKER_FIELD_TIMESTAMP = "timestamp"
const val COINONE_TICKER_FIELD_RESULT = "result"

data class CoinoneTicker(
    @SerializedName("volume") val volume: Double,
    @SerializedName("last") val last: Double,
    @SerializedName("yesterday_last") val yesterdayLast: String,
    @SerializedName("yesterday_low") val yesterdayLow: String,
    @SerializedName("high") val high: Double,
    @SerializedName("currency") val currency: String?,
    @SerializedName("low") val low: Double,
    @SerializedName("yesterday_first") val yesterdayFirst: String,
    @SerializedName("yesterday_volume") val yesterdayVolume: String,
    @SerializedName("yesterday_high") val yesterdayHigh: String,
    @SerializedName("first") val first: Double
) : ITicker {
    override fun toTicker(): Ticker {
        val diff = (last - first) / first * 100
        return Ticker(
            currency = currency ?: "",
            baseCurrency = "KRW",
            last = last,
            high = high,
            low = low,
            diff = diff,
            volume = volume * last
        )
    }
}