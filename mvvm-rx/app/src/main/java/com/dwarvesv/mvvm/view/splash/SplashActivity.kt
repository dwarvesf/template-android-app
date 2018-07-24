package com.dwarvesv.mvvm.view.splash

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.base.BaseNoAppBarActivity
import com.dwarvesv.mvvm.view.login.LoginActivity
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