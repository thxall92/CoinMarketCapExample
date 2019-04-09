package com.eunhye.com.coinmarketcapexample.ui.welcome

import android.content.Intent
import android.os.Bundle
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseActivity
import com.eunhye.com.coinmarketcapexample.databinding.WelcomeActivityBinding
import com.eunhye.com.coinmarketcapexample.ui.home.HomeActivity
import com.eunhye.com.coinmarketcapexample.ui.welcome.adapter.WelcomeAdapter
import com.eunhye.com.coinmarketcapexample.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity
    : BaseActivity<WelcomeActivityBinding>(R.layout.welcome_activity) {

    private val welcomeViewModel by viewModel<WelcomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            setLifecycleOwner(this@WelcomeActivity)
            this.welcomeViewModel = this@WelcomeActivity.welcomeViewModel.apply {
                nextActivity = {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                }
            }
            vpContent.adapter = WelcomeAdapter(supportFragmentManager).also {
                welcomeViewModel?.pageCnt = it.count
            }
        }
    }
}