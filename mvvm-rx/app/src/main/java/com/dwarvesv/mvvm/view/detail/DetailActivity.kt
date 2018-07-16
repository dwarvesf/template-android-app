package com.dwarvesv.mvvm.view.detail

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity

class DetailActivity : BaseNoAppBarActivity(), DetailFragment.InteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

            setFragment(DetailFragment.newInstance(), intent.extras)
        }
        setToolbarTitle(getString(R.string.activity_title_detail))
        setDisplayHomeAsUpEnabled(true)
    }

    private fun setFragment(detailFragment: DetailFragment, extras: Bundle) {

        detailFragment.arguments = extras
        replaceFragment(R.id.fragmentContainer, detailFragment)
    }
}
