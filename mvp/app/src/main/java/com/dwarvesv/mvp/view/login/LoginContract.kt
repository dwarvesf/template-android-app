package {{packageName}}.view.login

import {{packageName}}.base.BasePresenter
import {{packageName}}.base.BaseView
import {{packageName}}.data.request.LoginRequest
import {{packageName}}.data.response.LoginResponse


interface LoginContract {

    interface View : BaseView<Presenter> {
        fun loginSuccess(loginResponse: LoginResponse)
        fun loginFailed(throwable: Throwable)
    }


    interface Presenter : BasePresenter {
        fun login(request: LoginRequest)
    }
}
