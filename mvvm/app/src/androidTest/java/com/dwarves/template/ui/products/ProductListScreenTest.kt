package com.dwarves.template.ui.products

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.dwarves.template.R
import com.dwarves.template.util.childAtPosition
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ProductListScreenTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(ProductListActivity::class.java)

    @Test
    fun getProducts_whenOpenScreen_shouldDisplayItems() {
        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(withId(R.id.content), 0),
                isDisplayed()
        )).check(matches(withText("100 items")))

        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 0), 0),
                isDisplayed()
        )).check(matches(withText("Title 1")))

        onView(allOf(
                withId(R.id.tvDescription),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 0), 1),
                isDisplayed()
        )).check(matches(withText("Description 1")))

        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 1), 0),
                isDisplayed()
        )).check(matches(withText("Title 2")))

        onView(allOf(
                withId(R.id.tvDescription),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 1), 1),
                isDisplayed()
        )).check(matches(withText("Description 2")))
    }

    @Test
    fun removeProduct_whenRemoveFailed_shouldNotRemoveProduct() {
        onView(allOf(
                withId(R.id.ivRemove),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 0), 2),
                isDisplayed()
        )).perform(click())

        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 0), 0),
                isDisplayed()
        )).check(matches(withText("Title 1")))

        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(withId(R.id.content), 0),
                isDisplayed()
        )).check(matches(withText("100 items")))
    }

    @Test
    fun removeProduct_whenRemoveSuccess_shouldRemoveProduct() {
        onView(allOf(
                withId(R.id.ivRemove),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 1), 2),
                isDisplayed()
        )).perform(click())

        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(childAtPosition(withId(R.id.rvProduct), 1), 0),
                withText("Title 2"),
                isDisplayed()
        )).check(doesNotExist())

        onView(allOf(
                withId(R.id.tvTitle),
                childAtPosition(withId(R.id.content), 0),
                isDisplayed()
        )).check(matches(withText("99 items")))
    }

    @Test
    fun openProduct_whenClickingOnProduct_shouldOpenIt() {
        onView(allOf(
                withId(R.id.rvProduct),
                childAtPosition(withId(R.id.content), 1)
        )).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        onView(withText("Open 3")).inRoot(withDecorView(not(`is`(activityTestRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }
}
