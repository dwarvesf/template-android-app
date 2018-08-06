package com.dwarvesv.mvvm.view.splash

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface SplashViewModelInputs {
    var animationeName: PublishSubject<String>

}

interface SplashViewModelOutputs {
    val enableLogin: Observable<Boolean>
}

class SplashViewModel() : SplashViewModelInputs, SplashViewModelOutputs {
    override var animationeName: PublishSubject<String> = PublishSubject.create()

    override val enableLogin: PublishSubject<Boolean> = PublishSubject.create()

    val inputs: SplashViewModelInputs = this
    val outputs: SplashViewModelOutputs = this

    init {
        animationeName.subscribe { name ->
            if (name.equals("ice_cream_animation.json"))
                enableLogin.onNext(true)

        }
    }
}