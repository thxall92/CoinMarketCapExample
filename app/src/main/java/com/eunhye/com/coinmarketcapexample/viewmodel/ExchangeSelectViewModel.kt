package com.eunhye.com.coinmarketcapexample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.eunhye.com.coinmarketcapexample.base.BaseViewModel
import com.eunhye.com.coinmarketcapexample.data.enums.Exchange

class ExchangeSelectViewModel(
    private val application: Application
    ): BaseViewModel(){

    val liveExchanges = MutableLiveData<List<String>>()

    var selectedItemPosition = -1

    init {
        val exchanges = Exchange.values()

        liveExchanges.value = (exchanges.toList().map{
            application.getString(it.nameRes)
        }.sortedBy{it})

        Log.d("test exchanges", liveExchanges.toString())

    }
}