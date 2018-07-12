package com.dwarvesv.mvvm


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dwarvesv.mvvm.view.list.ListActivity
import com.dwarvesv.mvvm.view.list.ListFragment
import com.dwarvesv.mvvm.view.list.adapter.MvpViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by amitshekhar on 11/07/17.
 */

@RunWith(AndroidJUnit4::class)
class ListActivityTest {


    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(ListActivity::class.java)

    var listFragment: ListFragment = ListFragment.newInstance()

    @Before
    fun init() {
        mActivityRule.activity.setFragment(listFragment)
    }

    @Test
    fun recyclerViewTest() {
        Thread.sleep(2000)
        val recyclerView = onView(
                allOf(withId(R.id.recyclerView), isDisplayed()))
        recyclerView.perform(actionOnItemAtPosition<MvpViewHolder>(0, click()))
    }

}
