package com.eunhye.com.coinmarketcapexample.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
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

    private val KEY_BASE_CURRENCY = "KEY_BASE_CURRENCY"

    private val coinListViewModel by viewModel<CoinListViewModel>()

    companion object {

        fun newInstance(baseCurrency: String) = CoinListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_BASE_CURRENCY, baseCurrency)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinListViewModel.baseCurrency = arguments?.getString(KEY_BASE_CURRENCY)
        binding.run {
            coinListVM = coinListViewModel
            rvContent.run {
                adapter = object : BaseRecyclerViewAdapter<Ticker, CoinListItemBinding>(
                    layoutRes = R.layout.coin_list_item,
                    bindingVariableId = BR.ticker
                ) {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CoinListItemBinding> {
                        return super.onCreateViewHolder(parent, viewType).apply {
                            itemView.setOnClickListener {

                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(coinListViewModel.getAllTickers())
    }

    override fun onPause() {
        compositeDisposable.clear()
        coinListViewModel.finish()
        super.onPause()
    }
}