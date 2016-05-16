package com.mikhail.sportsnewshistoryrecords;

import android.app.Application;

import com.firebase.client.Firebase;

import timber.log.Timber;

/**
 * Main Application class
 * load Firebase server
 */
public class SportsLeagues extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
