package {{packageName}}.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import {{packageName}}.R
import {{packageName}}.base.BaseFragment
import {{packageName}}.data.request.LoginRequest
import {{packageName}}.data.response.LoginResponse
import {{packageName}}.data.source.local.user.UserLocalDataSource
import {{packageName}}.repository.UserRepository
import {{packageName}}.utils.CheckValidUtils.isPasswordValid
import {{packageName}}.utils.CheckValidUtils.isValidEmail
import {{packageName}}.utils.disposebag.DisposeBag
import {{packageName}}.utils.disposebag.disposedBy
import {{packageName}}.utils.getErrorHintEmail
import {{packageName}}.utils.getErrorHintPassword
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.support.v4.alert


class LoginFragment : BaseFragment(), LoginContract.View {

    private var listener: InteractionListener? = null
    override lateinit var presenter: LoginContract.Presenter
    private val bag = DisposeBag(this)
    private lateinit var isLoginEnable: Observable<Boolean>
    private lateinit var emailObservable: Observable<CharSequence>
    private lateinit var passwordObservable: Observable<CharSequence>

    companion object {
        fun getInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Fragment.InteractionListener")
        }
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        presenter = LoginPresenter(this, UserRepository.getInstance(userApi, UserLocalDataSource.getInstance(context!!)!!))
        RxView.clicks(btnLogin)
                .map { LoginRequest(etEmail.text.toString(), etPass.text.toString()) }
                .subscribe {
                    presenter.login(it)
                }
                .disposedBy(bag)

        emailObservable = RxTextView.textChanges(etEmail).skip(1)
        emailObservable.map(CharSequence::toString)
                .distinctUntilChanged()
                .subscribe {
                    setButtonLogin(isValidEmail(it))
                    setErrorHintEmail(!isValidEmail(it))
                }
                .disposedBy(bag)

        passwordObservable = RxTextView.textChanges(etPass).skip(1)
        passwordObservable.map(CharSequence::toString)
                .distinctUntilChanged()
                .subscribe {
                    setButtonLogin(isPasswordValid(it))
                    setErrorHintPasword(!isPasswordValid(it))
                }
                .disposedBy(bag)

        isLoginEnable =
                Observable.combineLatest(
                        emailObservable,
                        passwordObservable,
                        BiFunction { email, password ->
                            isValidEmail(email) && isPasswordValid(password)
                        })
        isLoginEnable.subscribe {
            setButtonLogin(it)
        }
    }

    private fun setButtonLogin(isEnable: Boolean) {
        btnLogin.isEnabled = isEnable
    }

    private fun setErrorHintPasword(isError: Boolean) {
        etPass.error = context?.let { getErrorHintPassword(isError, it) }
    }

    private fun setErrorHintEmail(isError: Boolean) {
        etEmail.error = context?.let { getErrorHintEmail(isError, it) }
    }

    override fun loginFailed(it: Throwable) {
        alert(R.string.login_failed) {
            title = getString(R.string.error)
            negativeButton(getString(R.string.ok), onClicked = {})
        }
        /*     .show()*/
        listener?.navigateToHome()
    }

    override fun loginSuccess(loginResponse: LoginResponse) {
        listener?.navigateToHome()
    }

    interface InteractionListener {

        fun navigateToHome()
    }
}