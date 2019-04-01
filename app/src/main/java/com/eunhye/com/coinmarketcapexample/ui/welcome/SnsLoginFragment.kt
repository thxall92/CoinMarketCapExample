package com.eunhye.com.coinmarketcapexample.ui.welcome

import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseFragment
import com.eunhye.com.coinmarketcapexample.databinding.SnsLoginFragmentBinding


class SnsLoginFragment
    : BaseFragment<SnsLoginFragmentBinding>(R.layout.sns_login_fragment) {

    companion object {
        fun newInstance() = SnsLoginFragment()
    }
}