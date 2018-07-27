package com.dwarvesv.mvp.base

import android.os.Bundle
import com.dwarvesv.mvp.R


open class BaseNoAppBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_appbar_layout)
    }
}