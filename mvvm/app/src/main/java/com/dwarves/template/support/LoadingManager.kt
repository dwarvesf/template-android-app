package com.dwarves.template.support

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

interface LoadingManager {
    fun show()

    fun hide()

    fun <T> bind(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            return@ObservableTransformer it
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { show() }
                    .doFinally { hide() }
        }
    }
}
