package com.eunhye.com.coinmarketcapexample.ext

import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.data.model.Ticker
import java.text.DecimalFormat

@BindingAdapter(value = ["tradeAmount"])
fun TextView.setTradeAmount(tradeAmount: Double) {
    var amount: Long = (tradeAmount).toLong()
    var fmtResId = if (amount < 1_000_000_000_000L) {
        amount /= 1_000_000L
        R.string.trade_amount_mega_fmt
    } else {
        amount /= 1_000_000_000L
        R.string.trade_amount_giga_fmt
    }
    text = String.format(context.getString(fmtResId), amount)
}

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")
@BindingAdapter(value = ["last"])
fun TextView.setLast(ticker: Ticker) {
    text = if (ticker.last ?: .0 > 1) {
        intFormat.format(ticker.last ?: 0)
    } else {
        doubleFormat.format(ticker.last ?: 0)
    }
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