package com.eunhye.com.coinmarketcapexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.eunhye.com.coinmarketcapexample.base.BaseViewModel
import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import com.eunhye.com.coinmarketcapexample.data.source.TickerDataSource
import io.reactivex.disposables.Disposable

class CoinListViewModel(private val tickerDataSource: TickerDataSource): BaseViewModel(){

    val liveTickers = MutableLiveData<List<Ticker>>()

    fun getAllTickers(): Disposable =
        tickerDataSource.getAllTicker { exchange, tickers ->
            val list = tickers.values.map {
                it.toTicker()
            }.sortedByDescending { it.volume * it.last }

            when (exchange) {
                Exchange.COINONE -> liveTickers.postValue(list)
            }

        }
}