package com.dwarvesv.mvvm.view.home


import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity
import com.dwarvesv.mvvm.view.list.ListActivity
import com.dwarvesv.mvvm.view.map.MapActivity
import org.jetbrains.anko.intentFor


class HomeActivity : BaseNoAppBarActivity(), HomeFragment.InteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(HomeFragment.newInstance())
        }
        setToolbarTitle(getString(R.string.activity_title_home))
        setDisplayHomeAsUpEnabled(false)
    }

    fun setFragment(homeFragment: HomeFragment) {
        replaceFragment(R.id.fragmentContainer, homeFragment)
    }

    override fun navigateToList() {
        startActivity(intentFor<ListActivity>())
    }

    override fun navigateToMap() {
        startActivity(intentFor<MapActivity>())
    }

}
