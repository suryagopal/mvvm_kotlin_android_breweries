package com.nags.breweries

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nags.breweires.ui.MainActivity
import com.nags.breweires.ui.home.adapter.BreweryViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BreweriesAppTest {

    private val mWaitTime = 5000L
    private val mBreweries = "Breweries"
    private val mBreweryDetails = "Brewery Details"
    private val mContact = "Contact"
    private val mWebsite = "Website"
    private val mMap = "Map"

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java)

    @Test
    fun testBreweryDetailsFragmentIsDisplayed() {
        Thread.sleep(mWaitTime)
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<BreweryViewHolder>(0, click())
            )
        onView(withText(mBreweryDetails)).check(matches(isDisplayed()))
        onView(withText(mContact)).check(matches(isDisplayed()))
        onView(withText(mWebsite)).check(matches(isDisplayed()))
        onView(withText(mMap)).check(matches(isDisplayed()))
    }

    @Test
    fun testBreweryDetailsBackClickDisplaysBreweriesList() {
        Thread.sleep(mWaitTime)
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<BreweryViewHolder>(0, click())
            )
        onView(withText(mBreweryDetails)).check(matches(isDisplayed()))
        activityRule.activity.runOnUiThread {
            activityRule.activity.onBackPressed()
        }
        Thread.sleep(mWaitTime)
        onView(withText(mBreweries)).check(matches(isDisplayed()))
    }

}