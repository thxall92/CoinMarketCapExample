package com.eunhye.com.coinmarketcapexample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.eunhye.com.coinmarketcapexample.base.BaseViewModel
import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.data.enums.getExchange
import com.eunhye.com.coinmarketcapexample.data.source.MainExchangeDataSource

class ExchangeSelectViewModel(
    private val application: Application,
    val mainExchangeDataSource: MainExchangeDataSource
) : BaseViewModel() {

    val liveExchanges = MutableLiveData<List<String>>()

    var selectedItemPosition = -1

    init {
        val exchanges = Exchange.values()

        liveExchanges.value = (exchanges.toList().map {
            application.getString(it.nameRes)
        }.sortedBy { it })
        mainExchangeDataSource.getSelectedExchange()?.let {
            selectedItemPosition = liveExchanges.value!!.indexOf(application.getString(it.nameRes))
        }
    }

    fun saveMainExchange(): Boolean {
        if(selectedItemPosition == -1) return false

        mainExchangeDataSource.saveMainExchange(
            getExchange(liveExchanges.value!![selectedItemPosition], application)!!
        )
        return true
    }


    fun getBaseCurrencies() = mainExchangeDataSource.getSelectedExchange()?.baseCurrencies!!

    fun getSelectedExchange() = mainExchangeDataSource.getSelectedExchange()!!
}