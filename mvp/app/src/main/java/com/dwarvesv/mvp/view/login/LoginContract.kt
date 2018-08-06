package com.dwarvesv.mvp.view.login

import com.dwarvesv.mvp.base.BasePresenter
import com.dwarvesv.mvp.base.BaseView
import com.dwarvesv.mvp.data.request.LoginRequest
import com.dwarvesv.mvp.data.response.LoginResponse


interface LoginContract {

    interface View : BaseView<Presenter> {
        fun loginSuccess(loginResponse: LoginResponse)
        fun loginFailed(throwable: Throwable)
    }


    interface Presenter : BasePresenter {
        fun login(request: LoginRequest)
    }
}
