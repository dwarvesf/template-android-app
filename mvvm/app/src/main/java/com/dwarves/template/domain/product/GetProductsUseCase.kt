package com.dwarves.template.domain.product

import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.domain.model.Product
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GetProductsUseCase(private val productRepo: ProductRepo) {

    fun execute(): Observable<List<Product>> {
        return productRepo.getProducts()
                .subscribeOn(Schedulers.io())
    }
}
