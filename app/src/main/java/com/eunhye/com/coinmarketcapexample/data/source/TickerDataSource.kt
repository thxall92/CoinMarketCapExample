package com.eunhye.com.coinmarketcapexample.data.source

import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.model.ITicker
import io.reactivex.disposables.Disposable

interface TickerDataSource {
    fun getAllTicker(response: (exchange: Exchange, tickers: Map<String, ITicker>) -> (Any)): Disposable
}