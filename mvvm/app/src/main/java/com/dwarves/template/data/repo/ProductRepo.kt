package com.dwarves.template.data.repo

import com.dwarves.template.data.api.ProductApi
import com.dwarves.template.data.model.ProductEntity
import com.dwarves.template.data.storage.ProductStorage
import com.dwarves.template.domain.model.Product
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
