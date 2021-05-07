package com.example.facultyprofile;

import android.app.Activity;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.facultyprofile.Activity.SearchActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;



/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExpressoTesting {

    public static final String STRING_TO_BE_TYPED = "Ganesh Neelakanta Iyer";
    public static final String DEPARTMENT = "CSE";

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule public ActivityScenarioRule<SearchActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SearchActivity.class);

    @Test
    public void checkProffessorName() {
        // Type text and then press the button.
        onView(withId(R.id.FACULTYNAME))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.search)).perform(click());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check that the text was changed.
        onView(withId(R.id.profilename)).check(matches(withText(STRING_TO_BE_TYPED)));
        onView(withId(R.id.selection)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.selection)).check(matches(withSpinnerText(containsString("AREA OF INTEREST"))));
        onData(anything()).inAdapterView(withId(R.id.areaofinterestlistview)).atPosition(0).perform(click());
        onView(withId(R.id.areaofinterestlistview)).check(matches(isDisplayed()));
        onView(withId(R.id.selection)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.selection)).check(matches(withSpinnerText(containsString("PUBLICATIONS"))));
        onView(withId(R.id.publicationslistview)).check(matches(isDisplayed()));

    }

    @Test
    public void Dropdown_department_JeyaKumar() {
        // Type text and then press the button.
        onView(withId(R.id.course_name)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.course_name)).check(matches(withSpinnerText(containsString("DEPARTMENT"))));
        onView(withId(R.id.DEPARTMENT))
                .perform(typeText(DEPARTMENT), closeSoftKeyboard());
        onView(withId(R.id.search)).perform(click());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.professorlistRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(15, click()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.profilename)).check(matches(withText("Jeyakumar")));
        onView(withId(R.id.selection)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.selection)).check(matches(withSpinnerText(containsString("AREA OF INTEREST"))));
        onData(anything()).inAdapterView(withId(R.id.areaofinterestlistview)).atPosition(0).perform(click());
        onView(withId(R.id.areaofinterestlistview)).check(matches(isDisplayed()));
        onView(withId(R.id.selection)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.selection)).check(matches(withSpinnerText(containsString("PUBLICATIONS"))));
        onView(withId(R.id.publicationslistview)).check(matches(isDisplayed()));
    }
    @Test
    public void Dropdown_Faculty() {
        // Type text and then press the button.
        onView(withId(R.id.course_name)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.course_name)).check(matches(withSpinnerText(containsString("FACULTY NAME"))));
    }
    @Test
    public void Image_Analysis() {
        // Type text and then press the button.
        onView(withId(R.id.course_name)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.course_name)).check(matches(withSpinnerText(containsString("INTERESTS"))));
        onView(withId(R.id.INTEREST))
                .perform(typeText("Image analysis"), closeSoftKeyboard());
        onView(withId(R.id.search)).perform(click());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.professorlistRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.profilename)).check(matches(withText("Padmavathi S")));
        onView(withId(R.id.selection)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.selection)).check(matches(withSpinnerText(containsString("AREA OF INTEREST"))));
        onData(anything()).inAdapterView(withId(R.id.areaofinterestlistview)).atPosition(0).perform(click());
        onView(withId(R.id.areaofinterestlistview)).check(matches(isDisplayed()));
        onView(withId(R.id.selection)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.selection)).check(matches(withSpinnerText(containsString("PUBLICATIONS"))));
        onView(withId(R.id.publicationslistview)).check(matches(isDisplayed()));
    }
    @Test
    public void Negative() {
        // Type text and then press the button.

        onView(withId(R.id.course_name)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.course_name)).check(matches(withSpinnerText(containsString("INTERESTS"))));
        onView(withId(R.id.INTEREST))
                .perform(typeText("Marana Mass"), closeSoftKeyboard());
        onView(withId(R.id.search)).perform(click());
    }
}