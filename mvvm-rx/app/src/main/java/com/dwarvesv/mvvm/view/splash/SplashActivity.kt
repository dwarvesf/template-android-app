package {{packageName}}.view.splash

import android.os.Bundle
import {{packageName}}.R
import {{packageName}}.base.BaseNoAppBarActivity
import {{packageName}}.view.login.LoginActivity
import org.jetbrains.anko.intentFor

class SplashActivity : BaseNoAppBarActivity(), SplashFragment.InteractionListener {

    private var splashFragment: SplashFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setFragment(SplashFragment.getInstance())
        }
    }

    private fun setFragment(splashFragment: SplashFragment) {
        replaceFragment(R.id.fragmentContainer, splashFragment)
    }


    override fun onDestroy() {
        splashFragment = null
        super.onDestroy()
    }


    override fun navigateToLogin() {
        startActivity(intentFor<LoginActivity>())
        finish()
    }


}