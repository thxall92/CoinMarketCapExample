package com.eunhye.com.coinmarketcapexample.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
                    (vpContent.adapter as? WelcomeAdapter)?.run{
                        liveCurrentPagePosition.value?.let{
//                            if ((getItem(it) as HomeActivity).saveMainExchange()) {
//                                startActivity(Intent(applicationContext, HomeActivity::class.java))
//                                finish()
//                            }else {
//                                Toast.makeText(applicationContext, getString(R.string.please_select_main_exchange), Toast.LENGTH_LONG).show()
//                            }
                        }
                    }

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