package com.dwarvesv.mvp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.base.BaseFragment
import com.dwarvesv.mvp.utils.disposebag.DisposeBag
import com.dwarvesv.mvp.utils.disposebag.disposedBy
import com.dwarvesv.mvp.view.list.ListActivity
import com.dwarvesv.mvp.view.map.MapActivity

import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.intentFor


class HomeFragment : BaseFragment() {

    private val bag = DisposeBag(this)


    companion object {

        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun setUpView(view: View, savedInstanceState: Bundle?) {

        RxView.clicks(btnMap)
                .subscribe {
                    navigateToMap()
                }
                .disposedBy(bag)

        RxView.clicks(btnList)
                .subscribe {
                    navigateToList()
                }
                .disposedBy(bag)
    }


    fun navigateToList() {
        activity?.startActivity(intentFor<ListActivity>())
    }

    fun navigateToMap() {
        activity?.startActivity(intentFor<MapActivity>())
    }


}
