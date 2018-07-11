package com.dwarvesv.mvvm.view.list


import android.content.Context
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.data.model.User.CREATOR.getListUserDummy
import com.dwarvesv.mvvm.utils.isNetworkConnected
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListPresenter(val view: ListContract.View, private val userRepository: com.dwarvesv.mvvm.repository.UserRepository, private var context: Context?) : ListContract.Presenter {

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

        userRepository.getListData(com.dwarvesv.mvvm.data.request.GetUsersRequest("")).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            if (it.body() != null) {

                                callHideIndicator(offset)

                                it.body()?.let { it1 ->
                                    view.onResultsReceived(it1, offset)
                                }

                            } else {
                                callHideIndicator(offset)
                                getListUserDummy()?.let { it1 -> view.onResultsReceived(it1, offset) }
                            }
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
