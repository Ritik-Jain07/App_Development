package com.example.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Espresso UI Testing
 */
@RunWith(AndroidJUnit4::class)
class AddToFavoriteBehaviorTest {

    /**
     * Use this create and launch the activity under test before each test,
     * and close it after each test.
     */
    @get:Rule
    var activityTestRule = androidx.test.rule.ActivityTestRule(MainActivity::class.java)

    /**
     * Use this test to uncheck the checkbox
     */
    @Test
    fun testAddToFavorite_CheckBoxNotChecked() {
        onView(withId(R.id.addToFavorite_BTN))
            .perform(click())
        onView(withId(R.id.itemFavorite_CB))
            .check(matches(isNotChecked()))
    }

    /**
     * Use this test to check the checkbox
     */
    @Test
    fun testAddToFavorite_CheckBoxChecked() {
        onView(withId(R.id.addToFavorite_BTN))
            .perform(click())
        onView(withId(R.id.itemFavorite_CB))
            .check(matches(isChecked()))
    }
}