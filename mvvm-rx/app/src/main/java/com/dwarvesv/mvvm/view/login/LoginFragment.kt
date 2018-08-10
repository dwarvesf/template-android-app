package {{packageName}}.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import {{packageName}}.R
import {{packageName}}.base.BaseFragment
import {{packageName}}.data.local.user.UserLocalDataSource
import {{packageName}}.repository.UserRepository
import {{packageName}}.utils.disposebag.DisposeBag
import {{packageName}}.utils.disposebag.disposedBy
import {{packageName}}.utils.getErrorHintEmail
import {{packageName}}.utils.getErrorHintPassword
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.support.v4.alert


class LoginFragment : BaseFragment() {

    private var listener: InteractionListener? = null

    private val bag = DisposeBag(this)
    private lateinit var viewModel: LoginViewModel

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

        viewModel = LoginViewModel(UserRepository.getInstance(userApi, UserLocalDataSource.getInstance(context!!)!!))

        RxView.clicks(btnLogin)
                .subscribe { _ ->
                    viewModel.inputs.doLogin(etEmail.text.toString(), etPass.text.toString())
                }
                .disposedBy(bag)

        onViewValid()
        onTextChanges()

    }

    private fun onViewValid() {
        viewModel.outputs.isLoginEnable
                .subscribe { isEnable ->
                    setButtonLogin(isEnable)
                }
                .disposedBy(bag)

        viewModel.outputs.isEmailEnable.subscribe { isEnable ->
            if (!isEnable)
                setButtonLogin(isEnable)

            setErrorHintEmail(!isEnable)
        }.disposedBy(bag)

        viewModel.outputs.isPasswordEnable.subscribe { isEnable ->
            if (!isEnable)
                setButtonLogin(isEnable)

            setErrorHintPasword(!isEnable)
        }.disposedBy(bag)

        viewModel.outputs.loginFailure.subscribe {
            alert(R.string.login_failed) {
                title = getString(R.string.error)
                negativeButton(getString(R.string.ok), onClicked = {})
            }
            /*     .show()*/
            listener?.navigateToHome()
        }
        viewModel.outputs.loginSuccess.subscribe {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        listener?.navigateToHome()
    }

    private fun onTextChanges() {

        RxTextView.textChanges(etPass)
                .map(CharSequence::toString)
                .distinctUntilChanged()
                .subscribe {
                    viewModel.inputs.passwordPublish.onNext(it)
                }
                .disposedBy(bag)

        RxTextView.textChanges(etEmail)
                .map(CharSequence::toString)
                .distinctUntilChanged()
                .subscribe {
                    viewModel.inputs.emailPublish.onNext(it)
                }
                .disposedBy(bag)

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


    interface InteractionListener {

        fun navigateToHome()
    }
}