package com.dwarvesv.mvvm.view.detail

import android.content.Intent
import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity

class DetailActivity : BaseNoAppBarActivity(), DetailFragment.InteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

            setFragment(DetailFragment.newInstance(), intent)
        }
        setToolbarTitle(getString(R.string.activity_title_detail))
        setDisplayHomeAsUpEnabled(true)
    }

    fun setFragment(detailFragment: DetailFragment, intent: Intent) {
        if (intent != null) {
            detailFragment.arguments = intent.extras
        }

        replaceFragment(R.id.fragmentContainer, detailFragment)
    }
}
