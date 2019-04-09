package com.eunhye.com.coinmarketcapexample.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ITEM : Any, B : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent?.context)
    .inflate(layoutRes, parent, false)) {

    protected var binding: B? = DataBindingUtil.bind(itemView)

    fun onBindViewHolder(item: Any?) {
        try {
            @Suppress("UNCHECKED_CAST")
            onViewCreated(item as? ITEM?)
            binding?.executePendingBindings()
        } catch (e: Exception) {
            itemView.visibility = View.GONE
        }
    }

    abstract fun onViewCreated(item: ITEM?)
}