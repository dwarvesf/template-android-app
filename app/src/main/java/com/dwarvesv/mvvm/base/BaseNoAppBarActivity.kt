package com.dwarvesv.mvvm.base

import android.os.Bundle
import com.dwarvesv.mvvm.R


open class BaseNoAppBarActivity : com.dwarvesv.mvvm.base.BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_appbar_layout)
    }
}