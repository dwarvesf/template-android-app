package {{packageName}}.base.list

import android.support.annotation.StringRes


interface BaseListView<T> {
    fun showRefreshing()
    fun hideRefreshing()
    fun showLoadMore()
    fun hideLoadMore()
    fun showAlertDialog(@StringRes titleResId: Int, @StringRes messageResId: Int)
}