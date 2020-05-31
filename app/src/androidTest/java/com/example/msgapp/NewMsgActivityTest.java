package com.example.msgapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class NewMsgActivityTest {
    @Rule
    public ActivityTestRule<NewMsgActivity> activityRule
            = new ActivityTestRule<>(NewMsgActivity.class);


    private NewMsgActivity activity = null;

    @Before
    public void setActivity(){
        activity = activityRule.getActivity();

    }

    @Test
    public void test_invalidNumber(){
        onView(withId(R.id.et_phone)).perform(replaceText("89789"));
        onView(withId(R.id.et_msg)).perform(replaceText("hi all!"));
        onView(withId(R.id.button_send)).perform(click());
        onView(withText("Invalid Number")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }


}
