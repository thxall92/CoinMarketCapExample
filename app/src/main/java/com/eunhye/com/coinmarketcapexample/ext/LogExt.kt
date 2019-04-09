package com.eunhye.com.coinmarketcapexample.ext

import android.util.Log
import com.eunhye.com.coinmarketcapexample.BuildConfig

internal fun logE(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.e("googry", msg)
    }
}