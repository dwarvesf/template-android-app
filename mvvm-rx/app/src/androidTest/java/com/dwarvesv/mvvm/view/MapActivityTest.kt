package com.dwarvesv.mvvm.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.view.map.MapActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MapActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MapActivity::class.java)

    @Test
    fun checkViewsDisplay() {
        onView(withContentDescription("Google Map"))
        onView(withId(R.id.mapView))
                .check(matches(isDisplayed()))
    }


}
