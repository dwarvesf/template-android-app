package com.dwarvesv.mvvm.view.detail

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity

class DetailActivity : BaseNoAppBarActivity(), DetailFragment.InteractionListener {

    private lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            detailFragment = DetailFragment.newInstance()

            if (intent != null) {
                detailFragment.arguments = intent.extras
            }

            replaceFragment(R.id.fragmentContainer, detailFragment)
        }
        setToolbarTitle(getString(R.string.activity_title_detail))
        setDisplayHomeAsUpEnabled(true)
    }

}
