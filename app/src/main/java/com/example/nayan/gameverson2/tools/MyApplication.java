package com.example.nayan.gameverson2.tools;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Nayan on 4/12/2017.
 */

public class MyApplication extends Application {
    private static MyApplication myInstance;
    private Tracker mTracker;

    public static MyApplication getMyInstance() {
        return myInstance;
    }

    public Context getAppContext() {
        return myInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myInstance = this;
//        DBManager.init(this)
//                //ENG
//                .createTable(MAppropriate.class)
//                .createTable(MFormation.class)
//                .createTable(MIdioms.class)
//                .createTable(MProverb.class)
//                .createTable(MSynoAnto.class)
//                .createTable(MTranslation.class)
//                .createTable(MGVerb.class)
//
//                //BAN
//                .createTable(MBagdhara.class)
//                .createTable(MEkkothai.class)
//                .createTable(MProbad.class)
//
//                //GK
//                .createTable(MGKDesh.class)
//                .createTable(MInternational.class)
//                .createTable(MSports.class)
//                .createTable(MMohashagor.class)
//                .createTable(MProbortok.class)
//
//                .build();
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//            mTracker = analytics.newTracker("UA-84590111-1");
            mTracker = analytics.newTracker("UA-84590111-1");
        }
        return mTracker;
    }
}
