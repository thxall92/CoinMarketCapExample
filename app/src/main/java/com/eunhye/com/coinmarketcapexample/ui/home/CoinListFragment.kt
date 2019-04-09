package com.eunhye.com.coinmarketcapexample.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseFragment
import com.eunhye.com.coinmarketcapexample.base.BaseRecyclerViewAdapter
import com.eunhye.com.coinmarketcapexample.base.BaseViewHolder
import com.eunhye.com.coinmarketcapexample.data.model.ITicker
import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import com.eunhye.com.coinmarketcapexample.databinding.CoinListFragmentBinding
import com.eunhye.com.coinmarketcapexample.databinding.CoinListItemBinding
import com.eunhye.com.coinmarketcapexample.viewmodel.CoinListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinListFragment
    : BaseFragment<CoinListFragmentBinding>(R.layout.coin_list_fragment) {

    val coinListViewModel by viewModel<CoinListViewModel>()

    companion object {
        fun newInstance() = CoinListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            setLifecycleOwner(this@CoinListFragment)
            this.coinListVM = this@CoinListFragment.coinListViewModel
            rvContent.run {
                adapter = object : BaseRecyclerViewAdapter<Ticker>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<Ticker, CoinListItemBinding>(
                            R.layout.coin_list_item, parent
                        ) {
                            override fun onViewCreated(item: Ticker?) {
                                binding?.run {
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