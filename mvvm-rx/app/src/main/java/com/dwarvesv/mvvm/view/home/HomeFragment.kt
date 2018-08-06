package com.dwarvesv.mvvm.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseFragment
import com.dwarvesv.mvvm.utils.disposebag.DisposeBag
import com.dwarvesv.mvvm.utils.disposebag.disposedBy
import com.dwarvesv.mvvm.view.list.ListActivity
import com.dwarvesv.mvvm.view.main.MainPagerAdapter
import com.dwarvesv.mvvm.view.map.MapActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.intentFor


class HomeFragment : BaseFragment() {

    private val bag = DisposeBag(this)
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MainPagerAdapter

    companion object {

        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        viewModel = HomeViewModel()
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
