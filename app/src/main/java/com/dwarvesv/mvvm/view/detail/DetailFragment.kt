package com.dwarvesv.mvvm.view.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.utils.Keys
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : com.dwarvesv.mvvm.base.BaseFragment(), DetailContract.View {

    private var listener: InteractionListener? = null
    override lateinit var presenter: DetailContract.Presenter

    companion object {

        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
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
        val args = arguments
        if (args != null) {
            val user: com.dwarvesv.mvvm.data.model.User = args.getParcelable(Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP) as com.dwarvesv.mvvm.data.model.User
            setData(user)
        }
    }

    private fun setData(user: com.dwarvesv.mvvm.data.model.User) {
        tvUserName.text = user.name
    }

    interface InteractionListener {
    }

}
