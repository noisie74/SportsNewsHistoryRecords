package com.mikhail.sportsnewshistoryrecords;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Mikhail on 5/3/16.
 */
public class SportsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);


    }
}
