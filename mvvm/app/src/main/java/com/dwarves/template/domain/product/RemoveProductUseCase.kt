package com.dwarves.template.domain.product

import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.util.Either
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber

class RemoveProductErrorResponse(val error: String)

class RemoveProductError(val id: Long, message: String): Throwable(message)

class RemoveProductUseCase(private val productRepo: ProductRepo, private val gson: Gson) {

    fun execute(id: Long): Single<Either<RemoveProductError, Long>> {
        return productRepo.removeProduct(id)
                .map { Either.value<RemoveProductError, Long>(it) }
                .onErrorReturn {
                    if (it is HttpException) {
                        // parse error body and custom error here
                        val errorBody = it.response().errorBody()
                        if (errorBody != null) {
                            val errorRes = gson.fromJson(errorBody.string(), RemoveProductErrorResponse::class.java)
                            return@onErrorReturn Either.error<RemoveProductError, Long>(RemoveProductError(id, errorRes.error))
                        }
                    }

                    Timber.w(it)
                    Either.error(RemoveProductError(id, "Something went wrong!!!"))
                }
                .subscribeOn(Schedulers.io())
    }
}
