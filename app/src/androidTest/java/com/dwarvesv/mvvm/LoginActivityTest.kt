package com.dwarvesv.mvvm

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.view.login.LoginActivity
import com.dwarvesv.mvvm.view.login.LoginFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    var loginFragment: LoginFragment = LoginFragment()
    @Before
    fun init() {
        mActivityRule.activity.setFragment(loginFragment)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.dwarvesv.mvvm", appContext.packageName)
    }
}
