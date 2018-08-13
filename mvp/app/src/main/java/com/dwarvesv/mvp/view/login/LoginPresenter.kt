package {{packageName}}.view.login

import {{packageName}}.data.request.LoginRequest
import {{packageName}}.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginPresenter(val view: LoginContract.View, private val userRepository: UserRepository) : LoginContract.Presenter {

    override fun login(request: LoginRequest) {
        userRepository.login(request).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { response ->
                            if (response.body() != null) {
                                response.body()?.let { loginResults ->
                                    view.loginSuccess(loginResults)
                                }
                            } else {
                                view.loginFailed(Throwable())
                            }
                        }, { throwAble ->
                    view.loginFailed(throwAble)
                })
    }

    init {
        view.presenter = this
    }


}
