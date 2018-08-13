package {{packageName}}.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Endless scroll listener for RecyclerView
 */
abstract class EndlessRecyclerOnScrollListener protected constructor(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.

    private var currentOffset = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = mLinearLayoutManager.itemCount
        val firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        val visibleThreshold = 7
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
            // End has been reached

            // Do something
            currentOffset += 10

            onLoadMore(currentOffset)

            loading = true
        }
    }


    abstract fun onLoadMore(currentOffset: Int)

    fun reset(previousTotal: Int, loading: Boolean) {
        this.previousTotal = previousTotal
        this.loading = loading
        this.currentOffset = 0
    }
}