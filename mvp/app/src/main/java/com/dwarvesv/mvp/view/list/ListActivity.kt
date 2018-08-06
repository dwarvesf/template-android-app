package com.dwarvesv.mvp.view.list

import android.os.Bundle
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.base.BaseNoAppBarActivity
import com.dwarvesv.mvp.data.model.User
import com.dwarvesv.mvp.utils.Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP
import com.dwarvesv.mvp.view.detail.DetailActivity
import org.jetbrains.anko.intentFor


class ListActivity : BaseNoAppBarActivity(), ListFragment.InteractionListener {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            listFragment = ListFragment.newInstance()
            replaceFragment(R.id.fragmentContainer, listFragment)
        }
        setToolbarTitle(getString(R.string.activity_title_list))
        setDisplayHomeAsUpEnabled(true)
    }

    override fun navigateToDetail(user: User) {
        startActivity(intentFor<DetailActivity>(BUNDLE_PARCELABLE_KEY_DATAMVP to user))
    }

}
