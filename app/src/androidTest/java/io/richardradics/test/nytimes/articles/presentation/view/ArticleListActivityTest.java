package io.richardradics.test.nytimes.articles.presentation.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jraska.falcon.FalconSpoon;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.richardradics.test.nytimes.R;
import io.richardradics.test.nytimes.articles.presentation.ArticleDetailActivity;
import io.richardradics.test.nytimes.articles.presentation.ArticleListActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static io.richardradics.test.nytimes.core.util.EspressoUtil.getCurrentActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ArticleListActivityTest {

    private Solo solo;

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityTestRule = new ActivityTestRule<>(ArticleListActivity.class, true, true);

    @Before
    public void setUp() {
        Intents.init();
        solo = new Solo(getInstrumentation(), getCurrentActivity());
    }

    @Test
    public void clickOnArticleTest() {
        solo.sleep(3000);

        FalconSpoon.screenshot(getCurrentActivity(), "click_articles_loaded");

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        solo.sleep(2000);

        FalconSpoon.screenshot(getCurrentActivity(), "click_articles_detail");

        Espresso.pressBack();

        solo.scrollDown();

        solo.sleep(2000);

        FalconSpoon.screenshot(getCurrentActivity(), "click_articles_go_back");

        solo.sleep(2000);

        solo.scrollDown();

        FalconSpoon.screenshot(getCurrentActivity(), "click_articles_go_back_and_down");

        intended(hasComponent(ArticleDetailActivity.class.getName()));

    }

    @After
    public void tearDown() {
        try {
            solo.finishOpenedActivities();
            solo.finalize();
        } catch (Throwable e) {
            // no-op
        }

        Intents.release();
    }
}
