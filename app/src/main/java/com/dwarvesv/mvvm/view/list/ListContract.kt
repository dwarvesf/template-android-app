package com.dwarvesv.mvvm.view.list


interface ListContract {

    interface View : com.dwarvesv.mvvm.base.BaseView<Presenter>, com.dwarvesv.mvvm.base.list.BaseListView<com.dwarvesv.mvvm.data.model.User> {
        fun onResultsReceived(listData: ArrayList<com.dwarvesv.mvvm.data.model.User>, offset: Int)
    }

    interface Presenter : com.dwarvesv.mvvm.base.BasePresenter {
        fun getListData(limit: Int, offset: Int, pullToRefresh: Boolean)
    }

}
