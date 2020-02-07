package com.example.modulotechtest.ui.main.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.modulotechtest.R
import com.example.modulotechtest.recyclerview.DevicesAdapter
import com.example.modulotechtest.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val heaterItem = 1
    private val lightItem = 6
    private val rollerItem = 10

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }




    @Test
    fun radioButtonAreVisible() {
        onView(withId(R.id.heaters_device_check)).check(matches(isDisplayed()))
        onView(withId(R.id.lights_device_check)).check(matches(isDisplayed()))
        onView(withId(R.id.roller_shutter_check)).check(matches(isDisplayed()))

    }

    @Test
    fun testRecyclerView(){
        onView(withId(R.id.recycler_view_devices)).check(matches(isDisplayed()))
    }

    @Test
    fun editHeaterIsOpened() {

        onView(withId(R.id.recycler_view_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DevicesAdapter.HeaterHolder>(
                    heaterItem,
                    click()
                )
            )
        onView(withId(R.id.name_text_view_heater)).check(matches(isDisplayed()))
    }
    @Test
    fun recyclerViewIsBackAfterEditHeater() {

        onView(withId(R.id.recycler_view_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DevicesAdapter.HeaterHolder>(
                    heaterItem,
                    click()
                )
            )
        pressBack()
        onView(withId(R.id.recycler_view_devices)).check(matches(isDisplayed()))
    }

    @Test
    fun editLightIsOpened() {

        onView(withId(R.id.recycler_view_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DevicesAdapter.LightHolder>(
                    lightItem,
                    click()
                )
            )
        onView(withId(R.id.name_text_view_light)).check(matches(isDisplayed()))
    }
    @Test
    fun recyclerViewIsBackAfterEditLight() {

        onView(withId(R.id.recycler_view_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DevicesAdapter.HeaterHolder>(
                    lightItem,
                    click()
                )
            )
        pressBack()
        onView(withId(R.id.recycler_view_devices)).check(matches(isDisplayed()))
    }

    @Test
    fun editRollerShutterIsOpened() {

        onView(withId(R.id.recycler_view_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DevicesAdapter.RollerShutterHolder>(
                    rollerItem,
                    click()
                )
            )
        onView(withId(R.id.name_text_view_roller_shutter)).check(matches(isDisplayed()))
    }
    @Test
    fun recyclerViewIsBackAfterEditRollerShutter() {

        onView(withId(R.id.recycler_view_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DevicesAdapter.HeaterHolder>(
                    rollerItem,
                    click()
                )
            )
        pressBack()
        onView(withId(R.id.recycler_view_devices)).check(matches(isDisplayed()))
    }

}

