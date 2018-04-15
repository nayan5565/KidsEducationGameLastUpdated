package com.example.nayan.gameverson2.tools;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Nayan on 4/12/2017.
 */

public class MyGoogleAnalytics {
    private static MyGoogleAnalytics instance;

    public static MyGoogleAnalytics getInstance() {
        if (instance == null)
            instance = new MyGoogleAnalytics();
        return instance;
    }

    public void setupAnalytics(String screenName) {
        MyApplication application = MyApplication.getMyInstance();
        Tracker mTracker = application.getDefaultTracker();
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }
}
