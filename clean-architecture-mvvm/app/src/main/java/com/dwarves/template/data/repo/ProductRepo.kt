package {{packageName}}.data.repo

import {{packageName}}.data.api.ProductApi
import {{packageName}}.data.model.ProductEntity
import {{packageName}}.data.storage.ProductStorage
import {{packageName}}.domain.model.Product
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

interface ProductRepo {
    fun getProducts(): Observable<List<Product>>
    fun removeProduct(id: Long): Single<Long>
}

class ProductRepoImpl(
        private val api: ProductApi,
        private val storage: ProductStorage,
        private val mapper: ProductMapper
) : ProductRepo {

    override fun getProducts(): Observable<List<Product>> {
        return Single.concat(storage.getProducts(), fetchProducts())
                .map { mapper.toProducts(it) }
                .toObservable()
    }

    private fun fetchProducts(): Single<List<ProductEntity>> {
        return api.getProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { storage.storeProduct(it) }
    }

    override fun removeProduct(id: Long): Single<Long> {
        return api.removeProduct(id)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapCompletable { storage.removeProduct(it) }
                .andThen(Single.just(id))
    }
}
