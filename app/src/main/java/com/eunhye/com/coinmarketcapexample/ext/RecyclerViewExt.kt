package com.eunhye.com.coinmarketcapexample.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunhye.com.coinmarketcapexample.base.BaseRecyclerViewAdapter

@BindingAdapter(value = ["replaceAll"])
fun RecyclerView.replaceAll(list: List<Any>?) =
    (this.adapter as? BaseRecyclerViewAdapter<Any>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }