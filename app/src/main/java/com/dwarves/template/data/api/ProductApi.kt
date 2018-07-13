package com.dwarves.template.data.api

import com.dwarves.template.data.model.ProductEntity
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class ProductApi {
    fun getProducts(): Single<List<ProductEntity>> {
        val products = (1..100).map {
            ProductEntity(it.toLong(), "Title $it", "Description $it")
        }

        return Single.just(products).delay(2, TimeUnit.SECONDS)
    }

    fun removeProduct(id: Long): Single<Long> {
        return Single.just(id).delay(1, TimeUnit.SECONDS)
    }
}
