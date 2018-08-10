package {{packageName}}.view.login

import android.os.Bundle
import {{packageName}}.R
import {{packageName}}.base.BaseNoAppBarActivity
import {{packageName}}.view.main.MainActivity
import {{packageName}}.view.splash.SplashFragment
import org.jetbrains.anko.intentFor

class LoginActivity : BaseNoAppBarActivity(), LoginFragment.InteractionListener {

    private var loginFragment: SplashFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setFragment(LoginFragment.getInstance())
        }
    }

    private fun setFragment(loginFragment: LoginFragment) {
        replaceFragment(R.id.fragmentContainer, loginFragment)
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