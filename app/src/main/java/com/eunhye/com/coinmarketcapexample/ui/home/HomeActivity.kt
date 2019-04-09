package com.eunhye.com.coinmarketcapexample.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseActivity
import com.eunhye.com.coinmarketcapexample.databinding.HomeActivityBinding
import com.eunhye.com.coinmarketcapexample.ext.addFragment

class HomeActivity :
    BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val coinListFragment by lazy { CoinListFragment.newInstance() }

    private val exitToast by lazy { Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_LONG) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(coinListFragment)
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
        }
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
}