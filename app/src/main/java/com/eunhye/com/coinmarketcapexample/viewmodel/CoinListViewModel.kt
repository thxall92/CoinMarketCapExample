package com.eunhye.com.coinmarketcapexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.eunhye.com.coinmarketcapexample.base.BaseViewModel
import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import com.eunhye.com.coinmarketcapexample.data.source.MainExchangeDataSource
import com.eunhye.com.coinmarketcapexample.data.source.ticker.TickerDataSource
import io.reactivex.disposables.Disposable

class CoinListViewModel(private val mainExchangeDataSource: MainExchangeDataSource) : BaseViewModel() {

    private val exchangeTickerDataSource = mainExchangeDataSource.getTickerDataSource()

    val liveTickers = MutableLiveData<List<Ticker>>()

    var baseCurrency: String? = null

    fun getAllTickers(): Disposable =
        exchangeTickerDataSource.getAllTicker(baseCurrency, success = {
            liveTickers.postValue(it)
        }, failed = {

        })

    fun finish() {
        exchangeTickerDataSource.finish()
    }

}