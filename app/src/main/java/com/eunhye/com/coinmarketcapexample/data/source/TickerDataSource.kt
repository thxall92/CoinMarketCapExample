package com.eunhye.com.coinmarketcapexample.data.source

import com.eunhye.com.coinmarketcapexample.data.model.ITicker
import io.reactivex.disposables.Disposable

interface TickerDataSource {
    fun getAllTicker(response: (market: String, tickers: Map<String, ITicker>) -> (Any)): Disposable
}