package com.mikhail.sportsnewshistoryrecords;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Main Application class
 * load Firebase server
 */
public class SportsLeagues extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
