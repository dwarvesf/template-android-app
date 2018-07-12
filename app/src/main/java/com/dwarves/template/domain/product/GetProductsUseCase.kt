package com.dwarves.template.domain.product

import com.dwarves.template.domain.model.Product
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GetProductsUseCase {

    fun execute(): Single<List<Product>> {
        val products = (1..100).map {
            Product(it.toLong(), "Title $it", "Description $it")
        }

        return Single.just(products).delay(2, TimeUnit.SECONDS)
    }
}
