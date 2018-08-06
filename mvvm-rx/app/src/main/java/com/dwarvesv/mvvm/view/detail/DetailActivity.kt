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
    }

    private fun setFragment(detailFragment: DetailFragment, extras: Bundle) {

        detailFragment.arguments = extras
        replaceFragment(R.id.fragmentContainer, detailFragment)
    }
}
