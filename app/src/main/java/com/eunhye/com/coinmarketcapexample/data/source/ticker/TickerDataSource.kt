package com.eunhye.com.coinmarketcapexample.data.source.ticker

import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import io.reactivex.disposables.Disposable

interface TickerDataSource {
    fun getAllTicker(baseCurrency: String?,
                     success: (tickers: List<Ticker>) -> Unit,
                     failed: (errorCode: String) -> Unit): Disposable

    fun finish()
}