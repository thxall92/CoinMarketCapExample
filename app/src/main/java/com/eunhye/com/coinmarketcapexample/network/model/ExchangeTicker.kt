package com.eunhye.com.coinmarketcapexample.network.model

import com.eunhye.com.coinmarketcapexample.data.model.Ticker

class ExchangeTicker(val exchangeName: String,
                     ticker: Ticker
)
    : Ticker(currency = ticker.currency,
        baseCurrency = ticker.baseCurrency,
        last = ticker.last,
        high = ticker.high,
        low = ticker.low,
        volume = ticker.volume,
        diff = ticker.diff)