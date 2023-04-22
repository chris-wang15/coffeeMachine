package com.coffeeit.coffeemachine

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @Test
    fun useAppContext() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.nfc_img))
            .perform(click())
        Espresso.onView(withId(R.id.main_page_title))
            .waitUntil(2000, withText(R.string.machine_id_des_success))
        Espresso.onView(withId(R.id.nfc_img))
            .perform(click())
        Espresso.onView(withId(R.id.selection_recycler_view))
            .waitUntil(2000, ViewMatchers.isDisplayed())
        Espresso.onView(withId(R.id.selection_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        Espresso.onView(withId(R.id.selection_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Espresso.onView(withId(R.id.selection_recycler_view))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithId(R.id.item_edit)
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Espresso.onView(withId(R.id.brew_button))
            .perform(click())
    }
}