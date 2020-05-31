package com.example.msgapp;


import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PlusButtonTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test_navPlusButton(){
        onView(withId(R.id.button_add)).perform(click());
        intended(hasComponent(NewMsgActivity.class.getName()));
    }

    @Test
    public void test_navPlusButton2(){
        onView(withId(R.id.button_add)).perform(click());
        Intent i = new Intent();
        activityRule.launchActivity(i);
    }


//    @Test
//    public void test_emptyMsgBody(){
//        onView(withId(R.id.button_add)).perform(click());
//        Intent i = new Intent();
//        onView(withId(R.id.et_phone)).perform(replaceText("8978986756"));
//        closeSoftKeyboard();
//        onView(withId(R.id.button_send)).perform(click());
//        closeSoftKeyboard();
//        pressBack();
//        onView(withText("Msg not saved")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//    }
}
