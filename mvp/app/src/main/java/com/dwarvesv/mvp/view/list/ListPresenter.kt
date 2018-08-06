package com.dwarvesv.mvp.view.list


import android.content.Context
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.repository.UserRepository
import com.dwarvesv.mvp.utils.isNetworkConnected
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListPresenter(val view: ListContract.View, private val userRepository: UserRepository, private var context: Context?) : ListContract.Presenter {

    init {
        view.presenter = this
    }

    override fun getListData(limit: Int, offset: Int, pullToRefresh: Boolean) {

        if (offset == 0) {
            if (!pullToRefresh) view.showRefreshing()
        } else view.showLoadMore()

        if (!isNetworkConnected(context)) {

            if (offset == 0)
                view.hideRefreshing()
            else
                view.hideLoadMore()

            view.showAlertDialog(R.string.title_connection_lost, R.string.msg_no_internet_connection)
            return
        }

        userRepository.getListData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { userList ->
                            if (userList != null) {
                                userList.let { getListResults ->
                                    view.onResultsReceived(getListResults, offset)
                                }
                            }

                            callHideIndicator(offset)
                        }
                        ,
                        {
                            callHideIndicator(offset)

                            view.showAlertDialog(R.string.title_connection_lost, R.string.msg_no_internet_connection)
                        })
    }

    private fun callHideIndicator(offset: Int) {
        if (offset == 0) {
            view.hideRefreshing()
        } else {
            view.hideLoadMore()
        }
    }


}
