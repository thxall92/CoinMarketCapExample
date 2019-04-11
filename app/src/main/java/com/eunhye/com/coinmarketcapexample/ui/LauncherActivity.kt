package com.eunhye.com.coinmarketcapexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.ui.home.HomeActivity
import com.eunhye.com.coinmarketcapexample.ui.welcome.WelcomeActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
//        startActivity(Intent(applicationContext, WelcomeActivity::class.java))
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }
}