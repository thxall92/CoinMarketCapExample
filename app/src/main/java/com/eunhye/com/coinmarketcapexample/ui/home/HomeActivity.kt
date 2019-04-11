package com.eunhye.com.coinmarketcapexample.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseActivity
import com.eunhye.com.coinmarketcapexample.databinding.HomeActivityBinding
import com.eunhye.com.coinmarketcapexample.base.BaseRecyclerViewAdapter
import com.eunhye.com.coinmarketcapexample.base.BaseViewHolder
import com.eunhye.com.coinmarketcapexample.databinding.ExchangeSelectItemBinding
import com.eunhye.com.coinmarketcapexample.viewmodel.ExchangeSelectViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity :
    BaseActivity<HomeActivityBinding>(com.eunhye.com.coinmarketcapexample.R.layout.home_activity) {

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    private var exitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            view = this@HomeActivity
            exchangeSelectVM = exchangeSelectViewModel
//        replaceFragmentInActivity(coinListFragment, R.id.fl_side_left)
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

            suplRoot.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
                override fun onPanelSlide(panel: View?, slideOffset: Float) {
                    icArrowForward.rotation = slideOffset * 180
                }

                override fun onPanelStateChanged(
                    panel: View?,
                    previousState: SlidingUpPanelLayout.PanelState?,
                    newState: SlidingUpPanelLayout.PanelState?
                ) {

                }
            })

            rvExchangeList.adapter = object : BaseRecyclerViewAdapter<String, ExchangeSelectItemBinding>(
                layoutRes = R.layout.exchange_select_item,
                bindingVariableId = BR.exchange
            ) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExchangeSelectItemBinding> {
                    return super.onCreateViewHolder(parent, viewType).apply {
                        itemView.setOnClickListener {
                            if (exchangeSelectViewModel.selectedItemPosition != adapterPosition) {
                                val prevPosition = exchangeSelectViewModel.selectedItemPosition
                                exchangeSelectViewModel.selectedItemPosition = adapterPosition
                                exchangeSelectViewModel.saveMainExchange()
                                notifyItemChanged(prevPosition)
                                notifyItemChanged(adapterPosition)
                                refreshPage()
                            }
                        }
                    }
                }

                override fun onBindViewHolder(holder: BaseViewHolder<ExchangeSelectItemBinding>, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.binding.run {
                        selectedPosition = exchangeSelectViewModel.selectedItemPosition
                        itemPosition = holder.adapterPosition
                    }
                }
            }

        }
        refreshPage()

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
            Toast.makeText(applicationContext, com.eunhye.com.coinmarketcapexample.R.string.description_back_finish, Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
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

    fun onOpenExchangeListClick() {
        binding.suplRoot.panelState = if (binding.suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            SlidingUpPanelLayout.PanelState.EXPANDED
        }
    }

    fun onOpenSideMenuClick() {
        binding.dlRoot.openDrawer(binding.flSideLeft)
    }
}