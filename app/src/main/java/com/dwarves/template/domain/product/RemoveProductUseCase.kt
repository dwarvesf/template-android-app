package com.dwarves.template.domain.product

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RemoveProductUseCase {

    fun execute(id: Long): Single<Long> {
        return Single.just(id).delay(1, TimeUnit.SECONDS)
    }
}
