package com.eunhye.com.coinmarketcapexample.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunhye.com.coinmarketcapexample.base.BaseActivity
import com.eunhye.com.coinmarketcapexample.databinding.HomeActivityBinding
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseRecyclerViewAdapter
import com.eunhye.com.coinmarketcapexample.base.BaseViewHolder
import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.databinding.ExchangeSelectItemBinding
import com.eunhye.com.coinmarketcapexample.viewmodel.ExchangeSelectViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity :
    BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    private var exitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        replaceFragmentInActivity(coinListFragment, R.id.fl_side_left)
        binding.run {
            view = this@HomeActivity
            exchangeSelectVM = exchangeSelectViewModel
            dlRoot.run {
                setScrimColor(Color.TRANSPARENT)
                addDrawerListener(object : DrawerLayout.DrawerListener {
                    override fun onDrawerStateChanged(newState: Int) {
                    }

                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    }

                    override fun onDrawerClosed(drawerView: View) {
                    }

                    override fun onDrawerOpened(drawerView: View) {
                    }
                })
            }

            suplRoot.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
                override fun onPanelSlide(panel: View?, slideOffset: Float) {
                    icArrowForward.rotation = slideOffset * 180
                }

                override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

                }
            })

            rvExchangeList.adapter = object : BaseRecyclerViewAdapter<String>(){
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<String, ExchangeSelectItemBinding>(
                            R.layout.exchange_select_item,
                            parent
                        ){

                            init {
                                itemView.setOnClickListener{
                                    if(exchangeSelectViewModel.selectedItemPosition != adapterPosition){
                                        exchangeSelectViewModel.selectedItemPosition = adapterPosition
                                        exchangeSelectViewModel.saveMainExchange()
                                        notifyDataSetChanged()
                                        refreshPage()
                                    }
                                }
                            }

                            override fun onViewCreated(item: String?) {
                                binding.run{
                                    exchange = item
                                    selectedPosition = exchangeSelectViewModel.selectedItemPosition
                                    itemPosition = adapterPosition
                                }
                            }
                        }
            }

            tlContent.setupWithViewPager(vpContent)
            vpContent.addOnAdapterChangeListener { viewPager, oldAdapter, _ ->
                (oldAdapter as? FragmentStatePagerAdapter)?.let {
                    val position = vpContent.currentItem
                    it.destroyItem(viewPager, position, it.getItem(position))
                    if (position > 0) {
                        it.destroyItem(viewPager, position - 1, it.getItem(position - 1))
                    }
                    val endPosition = it.count - 2
                    if (position < endPosition) {
                        it.destroyItem(viewPager, position + 1, it.getItem(position + 1))
                    }
                }
            }
        }
        refreshPage()
    }

    fun saveMainExchange(): Boolean {
        return exchangeSelectViewModel.saveMainExchange()
    }

    override fun onBackPressed() {
        if (binding.suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            binding.suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            return
        }

        if (binding.dlRoot.isDrawerOpen(binding.flSideLeft)) {
            binding.dlRoot.closeDrawer(binding.flSideLeft)
            return
        }
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

    fun onOpenSideMenuClick() {
        binding.dlRoot.openDrawer(binding.flSideLeft)
    }

    fun onOpenExchangeListClick() {
        binding.suplRoot.panelState = if (binding.suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            SlidingUpPanelLayout.PanelState.EXPANDED
        }
    }

    fun refreshPage() {
        binding.run {
            suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            tvExchange.text = getString(exchangeSelectViewModel.getSelectedExchange().nameRes)
            val pageTitles = exchangeSelectViewModel.getBaseCurrencies()

            vpContent.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
                override fun getItem(position: Int) = CoinListFragment.newInstance(pageTitles[position])

                override fun getCount() = pageTitles.size

                override fun getPageTitle(position: Int) = pageTitles[position]
            }
        }
    }
}