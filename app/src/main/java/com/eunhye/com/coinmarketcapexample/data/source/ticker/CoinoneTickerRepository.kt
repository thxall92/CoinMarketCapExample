package com.eunhye.com.coinmarketcapexample.data.source.ticker

import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import com.eunhye.com.coinmarketcapexample.ext.fromJson
import com.eunhye.com.coinmarketcapexample.ext.networkCommunication
import com.eunhye.com.coinmarketcapexample.network.api.CoinoneApi
import com.eunhye.com.coinmarketcapexample.network.model.COINONE_TICKER_FIELD_ERROR_CODE
import com.eunhye.com.coinmarketcapexample.network.model.COINONE_TICKER_FIELD_RESULT
import com.eunhye.com.coinmarketcapexample.network.model.COINONE_TICKER_FIELD_TIMESTAMP
import com.eunhye.com.coinmarketcapexample.network.model.CoinoneTicker
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinoneTickerRepository(val coinoneApi: CoinoneApi) :
    TickerDataSource {

    private val REQUEST_TIME_IN_MILLIS = 5000L

    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.newThread())
            .subscribe {
                coinoneApi.getAllTicker()
                    .networkCommunication()
                    .doOnSuccess {
                        if (it[COINONE_TICKER_FIELD_ERROR_CODE] != "0") {
                            failed.invoke("")
                        }
                    }
                    .filter {
                        it[COINONE_TICKER_FIELD_ERROR_CODE] == "0"
                    }
                    .map {
                        val gson = Gson()
                        it.filter {
                            !mutableListOf(COINONE_TICKER_FIELD_ERROR_CODE,
                                COINONE_TICKER_FIELD_TIMESTAMP,
                                COINONE_TICKER_FIELD_RESULT).contains(it.key)
                        }.map { tickerMap ->
                            gson.fromJson<CoinoneTicker>(tickerMap.value.toString())
                                .toTicker()
                        }
                    }
                    .subscribe({
                        success.invoke(it)
                    }) {
                    }
            }
    }

    override fun finish() {

    }
}