package {{packageName}}.view


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import {{packageName}}.R
import {{packageName}}.view.list.ListActivity
import {{packageName}}.view.list.adapter.MvpViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class ListActivityTest {


    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(ListActivity::class.java)



    @Test
    fun recyclerViewTest() {
        Thread.sleep(2000)
        val recyclerView = onView(
                allOf(withId(R.id.recyclerView), isDisplayed()))
        recyclerView.perform(actionOnItemAtPosition<MvpViewHolder>(0, click()))
    }

    @Test
    fun clickRandomItem() {
        //Magic happening
        Thread.sleep(2000)
        val x = getRandomRecyclerPosition(R.id.recyclerView)

        val recyclerView = onView(
                allOf(withId(R.id.recyclerView), isDisplayed()))
        recyclerView.perform(actionOnItemAtPosition<MvpViewHolder>(x, click()))
    }

    private fun getRandomRecyclerPosition(recyclerId: Int): Int {
        val ran = Random()
        //Get the actual drawn RecyclerView
        val recyclerView = mActivityRule
                .activity.findViewById(recyclerId) as RecyclerView

        //If the RecyclerView exists, get the item count from the adapter
        val n = if (recyclerView == null)
            1
        else
            recyclerView.adapter!!.itemCount

        //Return a random number from 0 (inclusive) to adapter.itemCount() (exclusive)
        return ran.nextInt(n)
    }

}
