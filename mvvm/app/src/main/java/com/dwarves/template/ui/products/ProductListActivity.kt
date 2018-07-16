package com.dwarves.template.ui.products

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.dwarves.template.R
import com.dwarves.template.ui.products.list.ProductItemViewModel
import com.dwarves.template.ui.products.list.ProductListAdapter
import com.dwarves.template.util.observeOnMain
import com.dwarves.template.util.showError
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_product_list.rvProduct
import kotlinx.android.synthetic.main.activity_product_list.tvTitle
import timber.log.Timber
import javax.inject.Inject

private const val STATE = "STATE"

class ProductListActivity : DaggerAppCompatActivity() {
    private val disposables = CompositeDisposable()

    @Inject
    lateinit var viewModel: ProductListViewModel
    @Inject
    lateinit var adapter: ProductListAdapter
    @Inject
    lateinit var loadingManager: ProductListLoadingManager

    @BindView(R.id.srlProduct)
    lateinit var srlProduct: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        setUpViews()
        bindViewModel(savedInstanceState?.getParcelable(STATE))
    }

    private fun setUpViews() {
        loadingManager.initialize()
        ButterKnife.bind(this)
        rvProduct.layoutManager = LinearLayoutManager(this)
        rvProduct.adapter = adapter
    }

    private fun bindViewModel(savedState: ProductListViewModel.SavedState?) {
        val output = viewModel.bind(ProductListViewModel.Input(
                savedState,
                Observable.just(1),
                RxSwipeRefreshLayout.refreshes(srlProduct),
                adapter.getEvents()
        ))

        bindProducts(output.products)
        bindTitle(output.title)
        bindMessages(output.messages)
    }

    private fun bindMessages(messages: Observable<String>) {
        disposables += messages.observeOnMain()
                .subscribe({ showError(it, Toast.LENGTH_SHORT) }, Timber::e)
    }

    private fun bindTitle(title: Observable<String>) {
        disposables += title.observeOnMain()
                .subscribe({ tvTitle.text = it }, Timber::e)
    }

    private fun bindProducts(products: Observable<List<ProductItemViewModel>>) {
        disposables += products.observeOnMain()
                .subscribe({
                    srlProduct.isRefreshing = false
                    adapter.submitList(it)
                }, Timber::e)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putParcelable(STATE, viewModel.createSavedState())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        viewModel.unbind()
        super.onDestroy()
    }
}
