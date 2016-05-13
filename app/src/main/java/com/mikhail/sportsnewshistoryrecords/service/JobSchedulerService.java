package com.mikhail.sportsnewshistoryrecords.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.NotificationCompat;

import com.mikhail.sportsnewshistoryrecords.activities.MainActivity;
import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.fragments.NotificationFragment;
import com.mikhail.sportsnewshistoryrecords.model.newswire.NytSportsResults;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Service for notifications
 */
public class JobSchedulerService extends JobService {

    private static final int NOTIFICATION_ID = 1;
    public Context context;

    public static final String NYT_ALL = "Pro football,Pro basketball,baseball,soccer,Hockey";
    public static final String NYT_FOOTBALL = "Pro%20Football";
    public static final String NYT_BASKETBALL = "Pro basketball";
    public static final String NYT_BASEBALL = "baseball";
    public static final String NYT_HOCKEY = "Hockey";
    public static final String NYT_SOCCER = "Soccer";

    public boolean topNewsCheck = false;
    public boolean footballCheck = false;
    public boolean basketballCheck = false;
    public boolean baseballCheck = false;
    public boolean hockeyCheck = false;
    public boolean soccerCheck = false;
    public boolean[] booleanArray;
    public NotificationManager notificationManager;
    public JobParameters params;


    @Override
    public boolean onStartJob(JobParameters params) {
        this.params = params;
        setBooleanArray();
        context = getApplicationContext();

        PersistableBundle persistableBundle = params.getExtras();
        booleanArray = persistableBundle.getBooleanArray(NotificationFragment.BOOLEAN_CODE);
        updateBooleans();
        setApiCall();
        return true;

    }

    /**
     * update value of boolean in the array
     */
    private void updateBooleans() {
        topNewsCheck = booleanArray[0];
        footballCheck = booleanArray[1];
        basketballCheck = booleanArray[2];
        baseballCheck = booleanArray[3];
        hockeyCheck = booleanArray[4];
        soccerCheck = booleanArray[5];
    }

    /**
     * set array of booleans
     * with checkboxes
     */
    private void setBooleanArray() {
        booleanArray = new boolean[6];
        booleanArray[0] = topNewsCheck;
        booleanArray[1] = footballCheck;
        booleanArray[2] = basketballCheck;
        booleanArray[3] = baseballCheck;
        booleanArray[4] = hockeyCheck;
        booleanArray[5] = soccerCheck;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    /**
     * create notifications
     */
    private void createNotifications(String title, String message, int icon) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(icon);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setContentIntent(pIntent);
        mBuilder.setPriority(Notification.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());

        /**
         * stop service
         */
        jobFinished(params, false);


    }

    /**
     * make api call for each checkbox
     */
    private void setApiCall() {
        if (topNewsCheck) {
            nytAllSportsNews();
        }
        if (footballCheck) {
            nytFootballNews();
        }
        if (basketballCheck) {
            nytBasketballNews();
        }
        if (baseballCheck) {
            nytBaseballNews();
        }
        if (hockeyCheck) {
            nytHockeyNews();
        }
        if (soccerCheck) {
            nytSoccerNews();
        }
    }

    /**
     * api call for all sports
     */
    public void nytAllSportsNews() {

        NytAPI.NytAPIRetrofit nytSports = NytAPI.create();

        Call<NytSportsResults> call = nytSports.response("all", "sports", NYT_ALL);

        call.enqueue(new Callback<NytSportsResults>() {
            @Override
            public void onResponse(Call<NytSportsResults> call, Response<NytSportsResults> response) {
                NytSportsResults nytSports = response.body();
                createNotifications(nytSports.getResults()[0].getTitle(),
                        nytSports.getResults()[0].getAbstractResult(), R.drawable.basketball24);
            }

            @Override
            public void onFailure(Call<NytSportsResults> call, Throwable t) {

            }
        });
    }

    /**
     * api call for football
     */
    public void nytFootballNews() {

        NytAPI.NytAPIRetrofit nytSports = NytAPI.create();

        Call<NytSportsResults> call = nytSports.response("all", "sports", NYT_FOOTBALL);

        call.enqueue(new Callback<NytSportsResults>() {
            @Override
            public void onResponse(Call<NytSportsResults> call, Response<NytSportsResults> response) {
                NytSportsResults nytSports = response.body();
                createNotifications(nytSports.getResults()[0].getTitle(),
                        nytSports.getResults()[0].getAbstractResult(), R.drawable.americanfootball24);
            }

            @Override
            public void onFailure(Call<NytSportsResults> call, Throwable t) {

            }
        });
    }

    /**
     * api call for basketball
     */
    public void nytBasketballNews() {

        NytAPI.NytAPIRetrofit nytSports = NytAPI.create();

        Call<NytSportsResults> call = nytSports.response("all", "sports", NYT_BASKETBALL);

        call.enqueue(new Callback<NytSportsResults>() {
            @Override
            public void onResponse(Call<NytSportsResults> call, Response<NytSportsResults> response) {
                NytSportsResults nytSports = response.body();
                createNotifications(nytSports.getResults()[0].getTitle(),
                        nytSports.getResults()[0].getAbstractResult(), R.drawable.basketball24);
            }

            @Override
            public void onFailure(Call<NytSportsResults> call, Throwable t) {

            }
        });
    }

    /**
     * api call for baseball
     */
    public void nytBaseballNews() {

        NytAPI.NytAPIRetrofit nytSports = NytAPI.create();

        Call<NytSportsResults> call = nytSports.response("all", "sports", NYT_BASEBALL);

        call.enqueue(new Callback<NytSportsResults>() {
            @Override
            public void onResponse(Call<NytSportsResults> call, Response<NytSportsResults> response) {
                NytSportsResults nytSports = response.body();
                createNotifications(nytSports.getResults()[0].getTitle(),
                        nytSports.getResults()[0].getAbstractResult(), R.drawable.baseball24);
            }

            @Override
            public void onFailure(Call<NytSportsResults> call, Throwable t) {

            }
        });
    }

    /**
     * api call for hockey
     */
    public void nytHockeyNews() {

        NytAPI.NytAPIRetrofit nytSports = NytAPI.create();

        Call<NytSportsResults> call = nytSports.response("all", "sports", NYT_HOCKEY);

        call.enqueue(new Callback<NytSportsResults>() {
            @Override
            public void onResponse(Call<NytSportsResults> call, Response<NytSportsResults> response) {
                NytSportsResults nytSports = response.body();
                createNotifications(nytSports.getResults()[0].getTitle(),
                        nytSports.getResults()[0].getAbstractResult(), R.drawable.hockey24);
            }

            @Override
            public void onFailure(Call<NytSportsResults> call, Throwable t) {

            }
        });
    }

    /**
     * api call for soccer
     */
    public void nytSoccerNews() {

        NytAPI.NytAPIRetrofit nytSports = NytAPI.create();

        Call<NytSportsResults> call = nytSports.response("all", "sports", NYT_SOCCER);

        call.enqueue(new Callback<NytSportsResults>() {
            @Override
            public void onResponse(Call<NytSportsResults> call, Response<NytSportsResults> response) {
                NytSportsResults nytSports = response.body();
                createNotifications(nytSports.getResults()[0].getTitle(),
                        nytSports.getResults()[0].getAbstractResult(), R.drawable.soccer24);
            }

            @Override
            public void onFailure(Call<NytSportsResults> call, Throwable t) {

            }
        });
    }

}