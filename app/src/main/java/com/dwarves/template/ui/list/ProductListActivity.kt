package com.dwarves.template.ui.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.dwarves.template.R
import com.dwarves.template.ui.list.adapter.ProductItemViewModel
import com.dwarves.template.ui.list.adapter.ProductListAdapter
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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

    @BindView(R.id.rvProduct)
    lateinit var rvProduct: RecyclerView

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
                adapter.getEvents()
        ))

        bindProducts(output.products)
    }

    private fun bindProducts(products: Observable<List<ProductItemViewModel>>) {
        disposables.add(products.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.submitList(it)
                }, Timber::e))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putParcelable(STATE, viewModel.createSavedStated())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        viewModel.unbind()
        super.onDestroy()
    }
}
