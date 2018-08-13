package {{packageName}}.ui.products

import android.os.Parcelable
import {{packageName}}.R
import {{packageName}}.domain.model.Product
import {{packageName}}.domain.product.GetProductsError
import {{packageName}}.domain.product.GetProductsUseCase
import {{packageName}}.domain.product.RemoveProductError
import {{packageName}}.domain.product.RemoveProductUseCase
import {{packageName}}.support.LoadingManager
import {{packageName}}.support.Navigator
import {{packageName}}.support.ResourceProvider
import {{packageName}}.ui.products.list.ProductItemViewModel
import {{packageName}}.ui.products.list.ProductItemViewModelMapper
import {{packageName}}.ui.products.list.ProductListAdapter
import {{packageName}}.util.EMPTY_STRING
import {{packageName}}.util.Either
import {{packageName}}.util.observeOnMain
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.parcel.Parcelize
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ProductListViewModel(
        private val loadingManagerManager: LoadingManager,
        private val mapper: ProductItemViewModelMapper,
        private val getProductsUseCase: GetProductsUseCase,
        private val removeProductUseCase: RemoveProductUseCase,
        private val resourceProvider: ResourceProvider,
        private val navigator: Navigator
) {
    private val disposables = CompositeDisposable()
    private val errors: PublishRelay<Throwable> = PublishRelay.create()
    private val products = BehaviorRelay.createDefault(emptyList<ProductItemViewModel>())

    @Parcelize
    data class SavedState(val products: List<ProductItemViewModel>) : Parcelable

    class Input(
            val savedState: SavedState?,
            val loadProducts: Observable<Any>,
            val pullToRefresh: Observable<Any>,
            val listEvents: Observable<Pair<Long, ProductListAdapter.EventType>>
    )

    class Output(
            val products: Observable<List<ProductItemViewModel>>,
            val title: Observable<String>,
            val messages: Observable<String>
    )

    fun bind(input: Input): Output {
        disposables.addAll(
                removeProduct(input),
                loadProducts(input),
                openProductDetail(input)
        )

        val products = products.hide()
        val title = products.map { it.size }
                .map { resourceProvider.getString(R.string.items, it) }
        val messages = errors.map { it.message ?: EMPTY_STRING }
                .filter { it.isNotBlank() }

        return Output(products, title, messages)
    }

    private fun openProductDetail(input: Input): Disposable {
        return input.listEvents.filter { it.second == ProductListAdapter.EventType.CLICK }
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOnMain()
                .flatMapCompletable { navigator.toProductDetail(it.first) }
                .subscribe({}, Timber::e)
    }

    private fun startRemove(id: Long) {
        val newProducts = products.value
                .map {
                    if (it.id == id) it.copy(loading = true)
                    else it
                }
        products.accept(newProducts)
    }

    private fun removeProduct(input: Input): Disposable {
        return input.listEvents
                .filter { it.second == ProductListAdapter.EventType.REMOVE }
                .doOnNext { event -> startRemove(event.first) }
                .flatMapSingle { removeProductUseCase.execute(it.first) }
                .observeOnMain()
                .subscribe({
                    when (it) {
                        is Either.Error<RemoveProductError, Long> -> handleRemoveFailed(it.error)
                        is Either.Value<RemoveProductError, Long> -> handleRemoveSuccess(it.value)
                    }
                }, Timber::e)
    }

    private fun handleRemoveSuccess(id: Long) {
        val newProducts = products.value
                .filter { it.id != id }
        products.accept(newProducts)
    }

    private fun handleRemoveFailed(error: RemoveProductError) {
        val newProducts = products.value
                .map {
                    if (it.id == error.id) it.copy(loading = false)
                    else it
                }
        products.accept(newProducts)
        errors.accept(error)
    }

    private fun loadProducts(input: Input): Disposable {
        val task = if (input.savedState == null) {
            Observable.merge(input.loadProducts, input.pullToRefresh)
                    .switchMap { loadProducts() }
        } else {
            products.accept(input.savedState.products)
            input.pullToRefresh.switchMap { loadProducts() }
        }

        return task.observeOnMain()
                .subscribe({
                    when (it) {
                        is Either.Error<GetProductsError, List<Product>> ->
                            errors.accept(it.error)
                        is Either.Value<GetProductsError, List<Product>> ->
                            products.accept(mapper.toItems(it.value))
                    }
                }, Timber::e)
    }

    private fun loadProducts(): Observable<Either<GetProductsError, List<Product>>> {
        return getProductsUseCase.execute()
                .compose(loadingManagerManager.bind())
    }

    fun createSavedState() = SavedState(products.value.map { it.copy(loading = false) })

    fun unbind() = disposables.dispose()
}
