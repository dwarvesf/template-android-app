package com.dwarvesv.mvvm.view.list

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity
import com.dwarvesv.mvvm.utils.Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP
import com.dwarvesv.mvvm.view.detail.DetailActivity
import org.jetbrains.anko.intentFor


class ListActivity : BaseNoAppBarActivity(), ListFragment.InteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(ListFragment.newInstance())
        }

        setToolbarTitle(getString(R.string.activity_title_list))
        setDisplayHomeAsUpEnabled(true)
    }

    fun setFragment(listFragment: ListFragment) {
        replaceFragment(R.id.fragmentContainer, listFragment)
    }


    override fun navigateToDetail(user: com.dwarvesv.mvvm.data.model.User) {
        startActivity(intentFor<DetailActivity>(BUNDLE_PARCELABLE_KEY_DATAMVP to user))
    }

}
