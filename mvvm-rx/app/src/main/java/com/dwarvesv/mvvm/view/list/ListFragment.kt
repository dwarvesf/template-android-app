package {{packageName}}.view.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import {{packageName}}.R
import {{packageName}}.base.BaseFragment
import {{packageName}}.data.local.user.UserLocalDataSource
import {{packageName}}.data.model.User
import {{packageName}}.repository.UserRepository
import {{packageName}}.utils.Constant.LIMIT
import {{packageName}}.utils.EndlessRecyclerOnScrollListener
import {{packageName}}.utils.getVisibilityView
import {{packageName}}.view.list.adapter.MvpAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.android.synthetic.main.layout_load_more.*
import org.jetbrains.anko.support.v4.alert


class ListFragment : BaseFragment() {


    private var listener: InteractionListener? = null

    private lateinit var mvpAdapter: MvpAdapter
    private var subscribe: Disposable? = null
    private lateinit var viewModel: ListViewModel
    private lateinit var onScrollListener: EndlessRecyclerOnScrollListener
    lateinit var linearLayoutManager: LinearLayoutManager
    private var mListData: ArrayList<User> = ArrayList()
    private var curOffset: Int = 0

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
        viewModel = ListViewModel(context, UserRepository.getInstance(userApi, UserLocalDataSource.getInstance(context!!)!!))


        viewModel.getListData(LIMIT, curOffset, false)
        viewModel.isShowRefreshing.subscribe {
            isShowRefreshing(it)
        }
        viewModel.isShowLoadMore.subscribe {
            isShowLoadMore(it)
        }
        viewModel.isShowAlertDialog.subscribe {
            showAlertDialog(R.string.title_connection_lost, R.string.msg_no_internet_connection)
        }
        viewModel.onResultsReceived.subscribe {
            onResultsReceived(it)
        }

        setUpRecyclerView()
        recyclerView.showShimmerAdapter()
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(activity)
        mvpAdapter = MvpAdapter()
        onScrollListener = object : {{packageName}}.utils.EndlessRecyclerOnScrollListener(linearLayoutManager) {
            override fun onLoadMore(currentOffset: Int) {
                curOffset = currentOffset
                viewModel.getListData(LIMIT, curOffset, false)
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
            curOffset = 0
            viewModel.getListData(LIMIT, curOffset, true)
        }

        subscribe = mvpAdapter.clickEvent
                .subscribe {
                    listener?.navigateToDetail(it)
                }

    }


    private fun showAlertDialog(titleResId: Int, messageResId: Int) {
        alert(messageResId) {
            title = getString(titleResId)
            negativeButton(getString(R.string.ok), onClicked = {})
        }.show()
    }

    private fun onResultsReceived(listData: ArrayList<User>) {
        if (curOffset == 0) mListData.clear()
        mListData.addAll(listData)
        mvpAdapter.submitList(mListData)

        layoutEmpty.visibility = getVisibilityView(listData.isEmpty())
        recyclerView.hideShimmerAdapter()

    }

    private fun isShowRefreshing(isShow: Boolean) {
        swipeRefreshLayout.isRefreshing = isShow
    }


    private fun isShowLoadMore(isShow: Boolean) {
        layoutLoadMore.visibility = getVisibilityView(isShow)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface InteractionListener {
        fun navigateToDetail(user: User)
    }

}
