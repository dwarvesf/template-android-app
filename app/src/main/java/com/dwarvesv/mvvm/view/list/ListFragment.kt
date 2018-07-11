package com.dwarvesv.mvvm.view.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.utils.Constant.LIMIT
import com.dwarvesv.mvvm.view.list.adapter.MvpAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.android.synthetic.main.layout_load_more.*
import org.jetbrains.anko.support.v4.alert


class ListFragment : com.dwarvesv.mvvm.base.BaseFragment(), ListContract.View {


    private var listener: InteractionListener? = null
    override lateinit var presenter: ListContract.Presenter
    private lateinit var mvpAdapter: MvpAdapter
    private var subscribe: Disposable? = null

    private lateinit var onScrollListener: com.dwarvesv.mvvm.utils.EndlessRecyclerOnScrollListener
    lateinit var linearLayoutManager: LinearLayoutManager
    private var mListData: ArrayList<com.dwarvesv.mvvm.data.model.User> = ArrayList()

    companion object {

        fun newInstance() = ListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_item, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Fragment.InteractionListener")
        }
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        presenter = ListPresenter(this, com.dwarvesv.mvvm.repository.UserRepository.getInstance(userApi), context)
        setUpRecyclerView()
        presenter.getListData(LIMIT, 0, false)
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(activity)
        mvpAdapter = MvpAdapter()
        onScrollListener = object : com.dwarvesv.mvvm.utils.EndlessRecyclerOnScrollListener(linearLayoutManager) {
            override fun onLoadMore(currentOffset: Int) {
                presenter.getListData(LIMIT, currentOffset, false)
            }
        }

        recyclerView.adapter = mvpAdapter
        val mDividerItemDecoration = DividerItemDecoration(context,
                linearLayoutManager.orientation)
        recyclerView.addItemDecoration(mDividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager


        recyclerView.addOnScrollListener(onScrollListener)

        swipeRefreshLayout.setOnRefreshListener {
            onScrollListener.reset(0, false)
            presenter.getListData(LIMIT, 0, true)
        }

        subscribe = mvpAdapter.clickEvent
                .subscribe {
                    listener?.navigateToDetail(it)
                }

    }

    override fun showAlertDialog(titleResId: Int, messageResId: Int) {
        alert(messageResId) {
            title = getString(titleResId)
            negativeButton(getString(R.string.ok), onClicked = {})
        }.show()
    }

    override fun onResultsReceived(listData: ArrayList<com.dwarvesv.mvvm.data.model.User>, offset: Int) {
        if (offset == 0) mListData.clear()
        mListData.addAll(listData)
        mvpAdapter.submitList(mListData)
        if (listData.isEmpty()) {
            layoutEmpty.visibility = View.VISIBLE
        } else {
            layoutEmpty.visibility = View.GONE
        }
    }

    override fun showRefreshing() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showLoadMore() {
        layoutLoadMore.visibility = View.VISIBLE
    }

    override fun hideLoadMore() {
        layoutLoadMore.visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface InteractionListener {
        fun navigateToDetail(user: com.dwarvesv.mvvm.data.model.User)
    }

}
