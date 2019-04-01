package com.eunhye.com.coinmarketcapexample.ui.welcome

import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseFragment
import com.eunhye.com.coinmarketcapexample.databinding.WelcomeFragmentBinding

class WelcomeFragment
    : BaseFragment<WelcomeFragmentBinding>(R.layout.welcome_fragment) {

    companion object {
        fun newInstance() = WelcomeFragment()
    }
}