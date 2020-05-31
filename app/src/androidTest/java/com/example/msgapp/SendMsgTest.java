package com.example.msgapp;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SendMsgTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test_sendMsg(){
        onView(withId(R.id.button_add)).perform(click());
        Intent i = new Intent();
        onView(withId(R.id.et_phone)).perform(replaceText("8978986756"));
        onView(withId(R.id.et_msg)).perform(replaceText("hi all!"));
        onView(withId(R.id.button_send)).perform(click());
        onView(withText("Msg Saved")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

}
