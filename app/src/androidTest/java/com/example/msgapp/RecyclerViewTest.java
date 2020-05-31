package com.example.msgapp;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test_navToIndividualActivity(){
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        Intent i = new Intent();
        activityRule.launchActivity(i);

    }

    //Not going to last item of list. Instead going to last item of screen or view
    @Test
    public void test_scrollToPosition() throws Exception
    {
        RecyclerView rv = activityRule.getActivity().findViewById(R.id.recycler_view);
        int itemCount = rv.getAdapter().getItemCount();
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemCount-1),click());
    }

    @Test
    public void test_scrollToText(){
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("Description 1")), click()));
    }

    @Test
    public void test_swipeLeftToDelete(){
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("8543837403")),swipeLeft()));
        onView(withText("Msg deleted")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void test_swipeRightToDelete(){
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("Description 1")),swipeRight()));
        onView(withText("Msg deleted")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }


//    @Test
//    public void testScrollingAt()throws Exception
//    {
//        // Scrolls till the position 20
//        onData(hasEntry(equalTo(ListViewSample.ROW_TEXT),is("List item: 20")))
//                .check(matches(isCompletelyDisplayed()));
//        //scrolls till the end
//        onData(hasEntry(equalTo(ListViewSample.ROW_TEXT),is("List item: 49")))
//                .check(matches(isCompletelyDisplayed()));
//    }
}
