package com.dwarvesv.mvvm.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.utils.disposebag.DisposeBag
import com.dwarvesv.mvvm.utils.disposebag.disposedBy
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : com.dwarvesv.mvvm.base.BaseFragment() {

    private var listener: InteractionListener? = null
    private val bag = DisposeBag(this)
    private lateinit var viewModel: HomeViewModel
    companion object {

        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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
        RxView.clicks(btnMap)
                .subscribe {
                    listener?.navigateToMap()
                }
                .disposedBy(bag)

        RxView.clicks(btnList)
                .subscribe {
                    listener?.navigateToList()
                }
                .disposedBy(bag)
    }


    interface InteractionListener {
        fun navigateToList()

        fun navigateToMap()
    }

}
