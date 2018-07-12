package com.dwarves.template.support

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

interface LoadingManager {
    fun show()

    fun hide()

    fun <T> bind(): SingleTransformer<T, T> {
        return SingleTransformer {
            return@SingleTransformer it
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { show() }
                    .doFinally { hide() }
        }
    }
}
