package com.dwarvesv.mvvm.view.login

import android.os.Bundle
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.view.home.HomeActivity
import org.jetbrains.anko.intentFor

class LoginActivity : com.dwarvesv.mvvm.base.BaseNoAppBarActivity(), LoginFragment.InteractionListener {

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
        startActivity(intentFor<HomeActivity>())
        finish()
    }


}