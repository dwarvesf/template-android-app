package com.dwarves.template.ui.list

import android.os.Parcelable
import com.dwarves.template.domain.product.GetProductsUseCase
import com.dwarves.template.domain.product.RemoveProductUseCase
import com.dwarves.template.ui.list.adapter.ProductItemViewModel
import com.dwarves.template.ui.list.adapter.ProductListAdapter
import com.dwarves.template.ui.list.adapter.toProductItems
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize
import timber.log.Timber

class ProductListViewModel(
        private val loadingManagerManager: ProductListLoadingManager,
        private val getProductsUseCase: GetProductsUseCase,
        private val removeProductUseCase: RemoveProductUseCase
) {
    private val disposables = CompositeDisposable()
    private val products = BehaviorRelay.createDefault(emptyList<ProductItemViewModel>())

    @Parcelize
    data class SavedState(
            val products: List<ProductItemViewModel>
    ) : Parcelable

    class Input(
            val savedState: SavedState?,
            val loadProducts: Observable<Any>,
            val listEvents: Observable<Pair<Long, ProductListAdapter.EventType>>
    )

    class Output(
            val products: Observable<List<ProductItemViewModel>>
    )

    fun bind(input: Input): Output {
        disposables.addAll(
                removeProduct(input),
                loadProducts(input)
        )

        return Output(products.hide())
    }

    fun unbind() {
        disposables.dispose()
    }

    private fun startRemove(id: Long) {
        val newProducts = products.value
                .map {
                    if (it.id == id)
                        it.copy(loading = true)
                    else
                        it
                }
        products.accept(newProducts)
    }

    private fun removeProduct(input: Input): Disposable {
        return input.listEvents
                .filter { it.second == ProductListAdapter.EventType.REMOVE }
                .doOnNext { event -> startRemove(event.first) }
                .flatMapSingle { removeProduct(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ removedId ->
                    val newProducts = products.value
                            .filter { it.id != removedId }
                    products.accept(newProducts)
                }, Timber::e)
    }

    private fun removeProduct(event: Pair<Long, ProductListAdapter.EventType>): Single<Long> {
        return removeProductUseCase.execute(event.first)
                .onErrorReturnItem(-1)
                .subscribeOn(Schedulers.io())
    }

    private fun loadProducts(input: Input): Disposable {
        val task = if (input.savedState == null)
            input.loadProducts.switchMapSingle { loadProducts() }
        else
            input.loadProducts.skip(1)
                    .switchMapSingle { loadProducts() }
                    .startWith(input.savedState.products)

        return task.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    products.accept(it)
                }, Timber::e)
    }

    private fun loadProducts(): Single<List<ProductItemViewModel>> {
        return getProductsUseCase.execute()
                .map { toProductItems(it) }
                .onErrorReturnItem(products.value)
                .subscribeOn(Schedulers.io())
                .compose(loadingManagerManager.bind())
    }

    fun createSavedStated(): SavedState {
        return SavedState(products.value.map { it.copy(loading = false) })
    }
}
