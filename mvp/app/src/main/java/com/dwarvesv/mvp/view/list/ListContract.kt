package {{packageName}}.view.list

import {{packageName}}.base.BasePresenter
import {{packageName}}.base.BaseView
import {{packageName}}.base.list.BaseListView
import {{packageName}}.data.model.User


interface ListContract {

    interface View : BaseView<Presenter>, BaseListView<User> {
        fun onResultsReceived(listData: ArrayList<User>, offset: Int)
    }

    interface Presenter : BasePresenter {
        fun getListData(limit: Int, offset: Int, pullToRefresh: Boolean)
    }

}
