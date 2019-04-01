package com.eunhye.com.coinmarketcapexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.base.BaseViewModel

class WelcomeViewModel
    : BaseViewModel() {

    var liveCurrentPagePosition = MutableLiveData<Int>().apply {
        value = 0

    }
    var liveBtnNextTextRes = MutableLiveData<Int>().apply {
        value = R.string.next
    }

    var pageCnt = 0

    var nextActivity: (() -> Unit)? = null

    fun onNextClick() {
        liveCurrentPagePosition.value?.run {
            if (pageCnt > this + 1) {
                when (this) {
                    0 -> liveBtnNextTextRes.value = R.string.start
                }
                liveCurrentPagePosition.value = this + 1
            } else {
                nextActivity?.invoke()
            }
        }
    }
}