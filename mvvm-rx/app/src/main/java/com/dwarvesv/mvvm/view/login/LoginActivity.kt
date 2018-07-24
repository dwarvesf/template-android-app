package com.dwarvesv.mvvm.view.login

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity
import com.dwarvesv.mvvm.view.main.MainActivity
import com.dwarvesv.mvvm.view.splash.SplashFragment
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