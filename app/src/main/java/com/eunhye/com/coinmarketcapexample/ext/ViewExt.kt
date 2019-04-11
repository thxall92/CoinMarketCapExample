package com.eunhye.com.coinmarketcapexample.ext

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isSelected")
internal fun View.isSelected(selected: Boolean) {
    isSelected = selected
}