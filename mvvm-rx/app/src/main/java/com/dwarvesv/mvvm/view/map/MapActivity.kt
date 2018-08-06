package com.dwarvesv.mvvm.view.map

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity


class MapActivity : BaseNoAppBarActivity(), MapFragment.InteractionListener {


    private lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(MapFragment.newInstance())
        }
        setToolbarTitle(getString(R.string.activity_title_map))
        setDisplayHomeAsUpEnabled(true)
    }

    private fun setFragment(mapFragment: MapFragment) {
        replaceFragment(R.id.fragmentContainer, mapFragment)
    }

}
