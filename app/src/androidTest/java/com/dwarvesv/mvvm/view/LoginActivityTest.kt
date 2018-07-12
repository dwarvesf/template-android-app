package com.dwarvesv.mvvm.view

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.R
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
    @JvmField
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    private var loginFragment: LoginFragment = LoginFragment()
    @Before
    fun init() {
        mActivityRule.activity.setFragment(loginFragment)
    }

    private val email = "thanh90@gmail.com"
    private val correct_password = "Qwert5"
    private val wrong_password = "passme123"

    @Test
    fun login_success() {

        Espresso.onView((withId(R.id.etEmail)))
                .perform(ViewActions.typeText(email))

        Espresso.onView(withId(R.id.etPass))
                .perform(ViewActions.typeText(correct_password))

        Espresso.onView(withId(R.id.btnLogin))
                .perform(ViewActions.click())
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.dwarvesv.mvvm", appContext.packageName)
    }

    @Test
    fun checkViewsDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.etPass))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
