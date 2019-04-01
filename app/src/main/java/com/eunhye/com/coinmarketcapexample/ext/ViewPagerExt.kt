package com.eunhye.com.coinmarketcapexample.ext

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

@BindingAdapter(value = ["currentPosition"])
fun ViewPager.currentPosition(position: Int) {
    currentItem = position
}