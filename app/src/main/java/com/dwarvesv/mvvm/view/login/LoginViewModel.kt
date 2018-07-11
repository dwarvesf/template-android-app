package com.dwarvesv.mvvm.view.login

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.dwarvesv.mvvm.data.request.LoginRequest
import com.dwarvesv.mvvm.data.response.LoginResponse
import com.dwarvesv.mvvm.repository.UserRepository
import com.dwarvesv.mvvm.utils.CheckValidUtils
import com.dwarvesv.mvvm.utils.disposebag.DisposeBag

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

interface LoginViewModelInputs {

    var emailPublish: PublishSubject<CharSequence?>

    var passwordPublish: PublishSubject<CharSequence?>

    fun doLogin(email: String, password: String)
}

interface LoginViewModelOutputs {

    val isLoginEnable: Observable<Boolean>

    val isEmailEnable: PublishSubject<Boolean>

    val isPasswordEnable: PublishSubject<Boolean>

    val loginSuccess: Observable<LoginResponse>

    val loginFailure: Observable<String>
}

class LoginViewModel(var context: Context?, lifeCycle: LifecycleOwner, private val userRepository: UserRepository) : LoginViewModelInputs, LoginViewModelOutputs {
    override val isEmailEnable: PublishSubject<Boolean> = PublishSubject.create()
    override val isPasswordEnable: PublishSubject<Boolean> = PublishSubject.create()

    val inputs: LoginViewModelInputs = this
    val outputs: LoginViewModelOutputs = this

    private val bag = DisposeBag(lifeCycle)

    override var emailPublish: PublishSubject<CharSequence?> = PublishSubject.create()
    override var passwordPublish: PublishSubject<CharSequence?> = PublishSubject.create()


    private val loginSuccessPublishSubject: PublishSubject<LoginResponse> = PublishSubject.create()
    override val loginSuccess: Observable<LoginResponse> = loginSuccessPublishSubject


    private val loginFailurePublishSubject: PublishSubject<String> = PublishSubject.create()
    override val loginFailure: Observable<String> = loginFailurePublishSubject


    override val isLoginEnable: Observable<Boolean> =
            Observable.combineLatest(
                    emailPublish,
                    passwordPublish,
                    BiFunction { email, password ->
                        CheckValidUtils.isValidEmail(email) && CheckValidUtils.isPasswordValid(password)
                    })

    init {
        emailPublish.subscribe {
            if (!it.isNullOrBlank())
                isEmailEnable.onNext(CheckValidUtils.isValidEmail(it))

        }
        passwordPublish.subscribe {
            if (!it.isNullOrBlank())
                isPasswordEnable.onNext(CheckValidUtils.isPasswordValid(it))
        }
    }


    override fun doLogin(email: String, password: String) {

        userRepository.login(LoginRequest(email = email, password = password)).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            if (it.body() != null) {
                                it.body()?.let { it1 ->
                                    loginSuccessPublishSubject.onNext(it1)
                                }
                            } else {
                                loginFailurePublishSubject.onNext(it.message())
                            }
                        }, {
                    it.message?.let { it1 -> loginFailurePublishSubject.onNext(it1) }
                })
    }
}