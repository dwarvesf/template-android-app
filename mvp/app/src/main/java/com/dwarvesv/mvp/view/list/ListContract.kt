package com.dwarvesv.mvp.view.list

import com.dwarvesv.mvp.base.BasePresenter
import com.dwarvesv.mvp.base.BaseView
import com.dwarvesv.mvp.base.list.BaseListView
import com.dwarvesv.mvp.data.model.User


interface ListContract {

    interface View : BaseView<Presenter>, BaseListView<User> {
        fun onResultsReceived(listData: ArrayList<User>, offset: Int)
    }

    interface Presenter : BasePresenter {
        fun getListData(limit: Int, offset: Int, pullToRefresh: Boolean)
    }

}
