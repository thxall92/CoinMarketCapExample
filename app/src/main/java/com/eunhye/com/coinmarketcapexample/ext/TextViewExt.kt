package com.eunhye.com.coinmarketcapexample.ext

import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.eunhye.com.coinmarketcapexample.R
import java.text.DecimalFormat

@BindingAdapter(value = ["tradeAmount"])
fun TextView.setTradeAmount(tradeAmount: Double) {
    var amount: Long = (tradeAmount).toLong()
    var fmtResId = when {
        amount < 1_000L -> {
            R.string.trade_amount_milli_fmt
        }
        amount < 1_000_000L -> {
            R.string.trade_amount_fmt
        }
        amount < 1_000_000_000L -> {
            amount /= 1_000L
            R.string.trade_amount_kilo_fmt
        }
        amount < 1_000_000_000_000L -> {
            amount /= 1_000_000L
            R.string.trade_amount_mega_fmt
        }
        else -> {
            amount /= 1_000_000_000L
            R.string.trade_amount_giga_fmt
        }
    }
    text = if(amount < 1_000L){
        String.format(context.getString(fmtResId), tradeAmount)
    }else{
        String.format(context.getString(fmtResId), amount)
    }
}

val doubleFormat = DecimalFormat("#,###.######")
@BindingAdapter(value = ["last"])
fun TextView.setLast(last: Double) {
    text = doubleFormat.format(last)
}

@BindingAdapter(value = ["autoSizeText"])
fun TextView.setAutoSizeText(autoSizeText: Boolean){
    TextViewCompat.setAutoSizeTextTypeWithDefaults(this,
        if(autoSizeText){
          TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        } else{
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE
        }
    )
}