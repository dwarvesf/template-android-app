package com.dwarvesv.mvp.view.detail

import android.os.Bundle
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.base.BaseNoAppBarActivity

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

    }

}
