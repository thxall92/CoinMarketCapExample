package com.eunhye.com.coinmarketcapexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.data.source.MainExchangeDataSource
import com.eunhye.com.coinmarketcapexample.ui.home.HomeActivity
import com.eunhye.com.coinmarketcapexample.ui.welcome.WelcomeActivity
import org.koin.android.ext.android.inject

class LauncherActivity : AppCompatActivity() {

    val mainExchangeDataSource by inject<MainExchangeDataSource>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)

        startApp()
    }

    fun startApp() {
        mainExchangeDataSource.getSelectedExchange().let {
            if (it == null) {
                startActivity(Intent(applicationContext, WelcomeActivity::class.java))
            } else {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
            }
            finish()
        }
    }
}