package com.eunhye.com.coinmarketcapexample.data.enums

import android.content.Context
import com.eunhye.com.coinmarketcapexample.R

enum class Exchange(val exchangeName: String, val nameRes: Int, val baseCurrencies: List<String>) {
     COINONE("Coinone", R.string.coinone, arrayListOf("KRW")),
     GOPAX("Gopax", R.string.gopax, arrayListOf("KRW", "BTC", "ETH"))
}

fun getExchange(name: String, context: Context): Exchange? {
     for(value in Exchange.values()){
          if(name == context.getString(value.nameRes)){
               return value
          }
     }
     return null
}