package com.eunhye.com.coinmarketcapexample.util

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.Gson
import okhttp3.internal.connection.Exchange


class PrefUtils(application: Application) {

    companion object {
        const val PREF_KEY_EXCHANGE = "PREF_KEY_EXCHANGE"
    }

    val sharedPref = PreferenceManager.getDefaultSharedPreferences(application)

    val gson = Gson()

    fun getEditor() = sharedPref.edit()
}