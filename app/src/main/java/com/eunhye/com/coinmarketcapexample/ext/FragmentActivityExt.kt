package com.eunhye.com.coinmarketcapexample.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.eunhye.com.coinmarketcapexample.R

internal fun FragmentActivity.addFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(R.id.content_frame, fragment)
        .commit()
}
