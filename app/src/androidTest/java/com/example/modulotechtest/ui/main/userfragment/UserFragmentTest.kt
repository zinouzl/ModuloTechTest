package com.example.modulotechtest.ui.main.userfragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.modulotechtest.R
import com.example.modulotechtest.ui.main.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class UserFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testUI() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.edit_text_first_name_user))
            .check(matches(isDisplayed()))
        onView(withId(R.id.hello_user_name)).check(matches(isDisplayed()))
        onView(withId(R.id.enable_edit_user_info)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_last_name_user)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_birthday_user)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_street_code_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_street_code_user)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_street_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_street_user)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_city_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_city_user)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_postal_code_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_postal_code_user)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_country_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_country_user)).check(matches(isDisplayed()))

    }

    @Test
    fun isEditDisabled() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.edit_text_first_name_user))
            .check(matches(not(isEnabled())))


        onView(withId(R.id.edit_text_last_name_user)).check(matches(not(isEnabled())))
        onView(withId(R.id.edit_text_birthday_user)).check(matches(not(isEnabled())))
        onView(withId(R.id.edit_text_street_code_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_street_code_user)).check(matches(not(isEnabled())))
        onView(withId(R.id.edit_text_street_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_street_user)).check(matches(not(isEnabled())))
        onView(withId(R.id.edit_text_city_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_city_user)).check(matches(not(isEnabled())))
        onView(withId(R.id.edit_text_postal_code_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_postal_code_user)).check(matches(not(isEnabled())))
        onView(withId(R.id.edit_text_country_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_country_user)).check(matches(not(isEnabled())))


    }

    @Test
    fun isSaveButtonNotVisible() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.save_user_button)).check(matches(not(isDisplayed())))
    }


    @Test
    fun editButtonClicked() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.enable_edit_user_info)).perform(click())
        onView(withId(R.id.edit_text_first_name_user))
            .check(matches(isEnabled()))
        onView(withId(R.id.edit_text_last_name_user)).check(matches(isEnabled()))
        onView(withId(R.id.edit_text_birthday_user)).check(matches(isEnabled()))
        onView(withId(R.id.edit_text_street_code_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_street_code_user)).check(matches(isEnabled()))
        onView(withId(R.id.edit_text_street_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_street_user)).check(matches(isEnabled()))
        onView(withId(R.id.edit_text_city_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_city_user)).check(matches(isEnabled()))
        onView(withId(R.id.edit_text_postal_code_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_postal_code_user)).check(matches(isEnabled()))
        onView(withId(R.id.edit_text_country_user)).perform(scrollTo())
        onView(withId(R.id.edit_text_country_user)).check(matches(isEnabled()))

        onView(withId(R.id.save_user_button)).check(matches(isDisplayed()))

    }


    @Test
    fun editButtonGoneAgain() {
        onView(withId(R.id.navigation_user)).perform(click())
        onView(withId(R.id.enable_edit_user_info)).perform(click())
        onView(withId(R.id.save_user_button)).perform(click())

        onView(withId(R.id.save_user_button)).check(matches(not(isDisplayed())))
    }

}