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


import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.R
import com.dwarvesv.mvvm.data.model.User
import com.dwarvesv.mvvm.utils.Keys.BundleKeys.BUNDLE_PARCELABLE_KEY_DATAMVP
import com.dwarvesv.mvvm.view.detail.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by amitshekhar on 11/07/17.
 */

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    var mActivityRule2: ActivityTestRule<DetailActivity> = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation()
                    .targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(BUNDLE_PARCELABLE_KEY_DATAMVP, User(id = "testId", name = "Test Data"))
            return result
        }
    }


    @Test
    fun checkViewsDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.tvUserName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvUserName)).check(ViewAssertions.matches(ViewMatchers.withText("Test Data")))
    }
}
