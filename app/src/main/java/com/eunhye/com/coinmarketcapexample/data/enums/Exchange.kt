package com.eunhye.com.coinmarketcapexample.data.enums

import com.eunhye.com.coinmarketcapexample.R

enum class Exchange(val nameRes: Int, val baseCurrencies: List<String>) {
     COINONE(R.string.coinone, arrayListOf("KRW"))
}