package io.richardradics.test.nytimes.core.util;

import android.app.Activity;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public final class EspressoUtil {

    private EspressoUtil() {
        // util class
    }

    public static Activity getCurrentActivity() {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = new Activity[1];
        getInstrumentation().runOnMainSync(() -> {
            java.util.Collection<Activity> activites = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
            activity[0] = Iterables.getOnlyElement(activites);
        });

        return activity[0];
    }
}
