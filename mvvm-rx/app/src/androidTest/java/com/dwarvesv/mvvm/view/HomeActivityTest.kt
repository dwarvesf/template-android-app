package com.dwarvesv.mvvm.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.view.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkViewsDisplay() {
        onView(withId(R.id.btnMap))
                .check(matches(isDisplayed()))

        onView(withId(R.id.btnList))
                .check(matches(isDisplayed()))

        onView(withId(R.id.btnMap)).check(matches(withText("Map Page")))

        onView(withId(R.id.btnList)).check(matches(withText("List Page")))
    }
}
