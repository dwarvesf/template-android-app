package com.dwarvesv.mvvm.view.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseFragment
import com.dwarvesv.mvvm.utils.disposebag.DisposeBag
import com.dwarvesv.mvvm.view.home.HomeFragment
import com.dwarvesv.mvvm.view.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment() {

    private var listener: InteractionListener? = null
    private val bag = DisposeBag(this)
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MainPagerAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.currentItem = 0
                listener?.setToolbarTitleName(getString(R.string.activity_title_home))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewpager.currentItem = 1
                listener?.setToolbarTitleName(getString(R.string.title_dashboard))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewpager.currentItem = 2
                listener?.setToolbarTitleName(getString(R.string.title_notifications))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    companion object {

        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Fragment.InteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        viewModel = HomeViewModel()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setupViewPager()
    }


    private fun setupViewPager() {

        adapter = MainPagerAdapter(fragmentManager)
        viewpager.offscreenPageLimit = 5
        viewpager.adapter = adapter

        adapter.addFragment(HomeFragment.newInstance())
        adapter.addFragment(HomeFragment.newInstance())
        adapter.addFragment(HomeFragment.newInstance())
        adapter.notifyDataSetChanged()

        //viewpager.post {   viewpager.currentItem = 0 }
        viewpager.currentItem = 0
    }

    interface InteractionListener {
        fun setToolbarTitleName(name: String)
    }

}
