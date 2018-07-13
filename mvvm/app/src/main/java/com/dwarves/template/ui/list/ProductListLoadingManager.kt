package com.dwarves.template.ui.list

import android.app.Activity
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.dwarves.template.R
import com.dwarves.template.support.LoadingManager

class ProductListLoadingManager(
        private val activity: Activity
) : LoadingManager {

    @BindView(R.id.content)
    lateinit var content: View
    @BindView(R.id.pbLoading)
    lateinit var loading: View

    fun initialize() {
        ButterKnife.bind(this, activity)
    }

    override fun show() {
        content.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    override fun hide() {
        content.visibility = View.VISIBLE
        loading.visibility = View.GONE
    }
}
