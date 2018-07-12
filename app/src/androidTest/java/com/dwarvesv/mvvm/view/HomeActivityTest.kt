/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.dwarvesv.mvvm.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.view.home.HomeActivity
import com.dwarvesv.mvvm.view.home.HomeFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(HomeActivity::class.java)

    private var homeFragment: HomeFragment = HomeFragment.newInstance()

    @Before
    fun init() {
        mActivityRule.activity.setFragment(homeFragment)
    }

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
