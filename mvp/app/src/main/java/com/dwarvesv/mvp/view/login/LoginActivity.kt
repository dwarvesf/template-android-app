package com.dwarvesv.mvp.view.login

import android.os.Bundle
import com.dwarvesv.mvp.R
import com.dwarvesv.mvp.base.BaseNoAppBarActivity
import com.dwarvesv.mvp.view.main.MainActivity

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