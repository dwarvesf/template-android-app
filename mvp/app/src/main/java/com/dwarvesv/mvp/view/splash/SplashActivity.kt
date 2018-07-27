package com.dwarvesv.mvp.view.splash

import android.os.Bundle
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.base.BaseNoAppBarActivity
import com.dwarvesv.mvp.view.login.LoginActivity
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