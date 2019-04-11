package com.eunhye.com.coinmarketcapexample.util

import android.app.Application
import android.preference.PreferenceManager
import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.ext.fromJson
import com.google.gson.Gson

class PrefUtils(application: Application) {

    companion object {
        const val PREF_KEY_EXCHANGE = "PREF_KEY_EXCHANGE"
    }

    val sharedPref = PreferenceManager.getDefaultSharedPreferences(application)

    val gson = Gson()

    fun getEditor() = sharedPref.edit()

    fun getExchange(): Exchange? {
        val jsonString = sharedPref.getString(PREF_KEY_EXCHANGE, null) ?: return null
        return gson.fromJson<Exchange>(jsonString)
    }
}