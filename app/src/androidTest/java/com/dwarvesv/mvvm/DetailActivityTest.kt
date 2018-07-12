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

package com.dwarvesv.mvvm


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.view.detail.DetailActivity
import com.dwarvesv.mvvm.view.detail.DetailFragment
import org.junit.Before
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
    var mActivityRule = ActivityTestRule(DetailActivity::class.java)

    var detailFragment: DetailFragment = DetailFragment.newInstance()

    @Before
    fun init() {

        //mActivityRule.activity.setFragment(detailFragment)

    }

    @Test
    fun checkViewsDisplay() {

        onView(withContentDescription("Google Map"))

    }
}
