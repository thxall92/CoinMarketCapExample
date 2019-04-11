package com.eunhye.com.coinmarketcapexample.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseFragment
import com.eunhye.com.coinmarketcapexample.base.BaseRecyclerViewAdapter
import com.eunhye.com.coinmarketcapexample.base.BaseViewHolder
import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import com.eunhye.com.coinmarketcapexample.databinding.CoinListFragmentBinding
import com.eunhye.com.coinmarketcapexample.databinding.CoinListItemBinding
import com.eunhye.com.coinmarketcapexample.viewmodel.CoinListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinListFragment
    : BaseFragment<CoinListFragmentBinding>(R.layout.coin_list_fragment) {

    private val coinListViewModel by viewModel<CoinListViewModel>()

    companion object {

        const val KEY_BASE_CURRENCY = "KEY_BASE_CURRENCY"

        fun newInstance(baseCurrency: String) = CoinListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_BASE_CURRENCY, baseCurrency)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            coinListVM = coinListViewModel
            rvContent.run {
                adapter = object : BaseRecyclerViewAdapter<Ticker>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<Ticker, CoinListItemBinding>(
                            R.layout.coin_list_item, parent
                        ) {
                            init {
                                itemView.setOnClickListener {
                                }
                            }
                            override fun onViewCreated(item: Ticker?) {
                                binding.run {
                                    ticker = item
                                }
                            }
                        }
                }
            }
        }
        compositeDisposable.add(coinListViewModel.getAllTickers())

    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(coinListViewModel.getAllTickers())
    }

    override fun onPause() {
        compositeDisposable.clear()
        super.onPause()
    }
}