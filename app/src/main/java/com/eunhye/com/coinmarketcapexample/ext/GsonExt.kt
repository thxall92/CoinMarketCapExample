package com.eunhye.com.coinmarketcapexample.ext

import com.google.gson.Gson

inline fun <reified T> Gson.fromJson(jsonString: String) = fromJson(jsonString, T::class.java)
