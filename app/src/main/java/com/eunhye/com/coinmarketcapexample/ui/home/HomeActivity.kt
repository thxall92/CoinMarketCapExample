package com.eunhye.com.coinmarketcapexample.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseActivity
import com.eunhye.com.coinmarketcapexample.data.enums.Exchange
import com.eunhye.com.coinmarketcapexample.databinding.HomeActivityBinding

class HomeActivity :
    BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val exitToast by lazy { Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_LONG) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        replaceFragmentInActivity(coinListFragment, R.id.fl_side_left)
        binding.run {
            view = this@HomeActivity
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
        }


        refreshPage()
    }


    override fun onBackPressed() {
        if (binding.dlRoot.isDrawerOpen(binding.flSideLeft)) {
            binding.dlRoot.closeDrawer(binding.flSideLeft)
            return
        }
        if (exitToast.view.windowVisibility == View.VISIBLE) {
            super.onBackPressed()
        } else {
            exitToast.show()
        }
    }

    fun onOpenSideMenuClick() {
        binding.dlRoot.openDrawer(binding.flSideLeft)
    }

    fun refreshPage() {
        val pageTitles = Exchange.COINONE.baseCurrencies
        binding.run {
            vpContent.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
                override fun getItem(position: Int) = CoinListFragment.newInstance(pageTitles[position])
                override fun getCount() = pageTitles.size
                override fun getPageTitle(position: Int) = pageTitles[position]
            }
        }
    }
}