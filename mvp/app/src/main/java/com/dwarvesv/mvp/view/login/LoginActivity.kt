package {{packageName}}.view.login

import android.os.Bundle
import {{packageName}}.R
import {{packageName}}.base.BaseNoAppBarActivity
import {{packageName}}.view.main.MainActivity

import org.jetbrains.anko.intentFor

class LoginActivity : BaseNoAppBarActivity(), LoginFragment.InteractionListener {

    private var loginFragment: LoginFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            loginFragment = LoginFragment.getInstance()
            replaceFragment(R.id.fragmentContainer, loginFragment!!)
        }
    }

    override fun onDestroy() {
        loginFragment = null
        super.onDestroy()
    }


    override fun navigateToHome() {
        startActivity(intentFor<MainActivity>())
        finish()
    }


}