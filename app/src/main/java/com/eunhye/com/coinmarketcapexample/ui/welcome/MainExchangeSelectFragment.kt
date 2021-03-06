package com.eunhye.com.coinmarketcapexample.ui.welcome

import android.os.Bundle
import android.view.View
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseFragment
import com.eunhye.com.coinmarketcapexample.databinding.MainExchangeSelectFragmentBinding
import com.eunhye.com.coinmarketcapexample.viewmodel.ExchangeSelectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainExchangeSelectFragment :
    BaseFragment<MainExchangeSelectFragmentBinding>(R.layout.main_exchange_select_fragment) {

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    companion object {
        fun newInstance() = MainExchangeSelectFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}