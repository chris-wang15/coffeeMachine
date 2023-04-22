package com.coffeeit.coffeemachine

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException


fun clickChildViewWithId(id: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "clickChildViewWithId $id"
        }

        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<View>(id)?.performClick()
        }
    }
}

fun ViewInteraction.waitUntil(timeout: Long, matcher: Matcher<View>): ViewInteraction {
    val startTime = System.currentTimeMillis()
    val endTime = startTime + timeout

    do {
        try {
            check(matches(matcher))
            return this
        } catch (e: AssertionFailedError) {
            Thread.sleep(50)
        }
    } while (System.currentTimeMillis() < endTime)

    throw TimeoutException()
}