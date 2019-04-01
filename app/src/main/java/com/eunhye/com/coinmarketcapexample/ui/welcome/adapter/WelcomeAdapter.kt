package com.eunhye.com.coinmarketcapexample.ui.welcome.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.eunhye.com.coinmarketcapexample.ui.welcome.SnsLoginFragment
import com.eunhye.com.coinmarketcapexample.ui.welcome.WelcomeFragment

class WelcomeAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>().apply {
        add(WelcomeFragment.newInstance())
        add(SnsLoginFragment.newInstance())
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}