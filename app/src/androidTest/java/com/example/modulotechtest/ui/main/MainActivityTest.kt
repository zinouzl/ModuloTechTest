package com.example.modulotechtest.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SdkSuppress
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.modulotechtest.R
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }


    @Test
    fun testUI() {
        val activity = mActivityTestRule.activity
        assertNotNull(activity)
        assertNotNull(activity.findViewById(R.id.nav_view))
        assertNotNull(activity.findViewById(R.id.nav_host_fragment))
    }

    @Test
    @SdkSuppress(minSdkVersion = 24)
    fun testMinSdk() {
        val activity = mActivityTestRule.activity
        assertNotNull(activity)
        assertNotNull(activity.findViewById(R.id.nav_view))
        assertNotNull(activity.findViewById(R.id.nav_host_fragment))

    }

    @Test
    fun navigationToUserTest() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.edit_text_first_name_user)).check(matches(isDisplayed()))
    }

    @Test
    fun navigationBackToDevice() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.edit_text_first_name_user)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.recycler_view_devices)).check(matches(isDisplayed()))
    }
}