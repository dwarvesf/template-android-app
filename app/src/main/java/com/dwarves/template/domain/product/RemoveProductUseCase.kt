package com.dwarves.template.domain.product

import com.dwarves.template.data.repo.ProductRepo
import io.reactivex.Single

class RemoveProductUseCase(private val productRepo: ProductRepo) {

    fun execute(id: Long): Single<Long> {
        return productRepo.removeProduct(id)
    }
}
