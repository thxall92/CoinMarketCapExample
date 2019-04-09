package com.eunhye.com.coinmarketcapexample.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eunhye.com.coinmarketcapexample.R
import com.eunhye.com.coinmarketcapexample.ext.addFragment

abstract class BaseFragmentBindingActivity : AppCompatActivity() {
    protected lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_fragment_binding_activity)
        fragment = createFragment().apply {
            addFragment(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun createFragment(): Fragment

}