package com.dwarvesv.mvp.view.detail

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.base.BaseFragment
import com.dwarvesv.mvp.data.model.User
import com.dwarvesv.mvp.utils.Keys
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment(), DetailContract.View {

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
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args = arguments
        if (args != null) {
            val user: User = args.getParcelable(Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP) as User
            setData(user)
        }
    }

    private fun setData(user: User) {
        (activity as AppCompatActivity).setTitle(user.name)
    }

    interface InteractionListener

}
