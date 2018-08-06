package com.dwarvesv.mvvm.base

import android.os.Bundle
import android.view.MenuItem
import com.dwarvesv.mvvm.R


open class BaseNoAppBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_appbar_layout)
    }

    fun setToolbarTitle(title: String?) {
        supportActionBar!!.title = title
    }

    fun setDisplayHomeAsUpEnabled(enabled: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(enabled)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setHomeButtonEnabled(enabled: Boolean) {
        supportActionBar!!.setHomeButtonEnabled(enabled)
    }
}