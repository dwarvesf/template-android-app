package com.dwarves.template.domain.product

import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.domain.model.Product
import com.dwarves.template.util.Either
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber

class GetProductsError(message: String): Throwable(message)

class GetProductsUseCase(private val productRepo: ProductRepo) {

    fun execute(): Observable<Either<GetProductsError, List<Product>>> {
        return productRepo.getProducts()
                .map { Either.value<GetProductsError, List<Product>>(it) }
                .onErrorReturn {
                    if (it is HttpException) {
                        // parse error body and custom error here
                        return@onErrorReturn Either.error(GetProductsError("Server error"))
                    }

                    Timber.w(it)
                    Either.error(GetProductsError("Something went wrong!!!"))
                }
                .subscribeOn(Schedulers.io())
    }
}
