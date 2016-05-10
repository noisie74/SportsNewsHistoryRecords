package com.mikhail.sportsnewshistoryrecords.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.mikhail.sportsnewshistoryrecords.MainActivity;
import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.NotificationFragment;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 5/10/16.
 */
public class JobSchedulerService extends JobService {

    NytAPI latestNewsService;
    public ArrayList<NytSportsResults> articleLists;
    public String sections = "all";
    public String chooseMagazineSource = "all";
    private static final int NOTIFICATION_ID = 1;
    Context context;
    NytSportsResults nytSportsResults;
    SharedPreferences sharedPreferences;

    boolean topNewsCheck = true;
    boolean footballCheck;
    boolean basketballCheck;
    boolean baseballCheck;
    boolean hockeyCheck;
    boolean soccerCheck;
    boolean[] booleenArray;
    ArrayList<NytSportsObjects> sportsNewsList = new ArrayList<>();
    NotificationManager notificationManager;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Notifications", "StartCommand sent");
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        booleenArray = intent.getBooleanArrayExtra(NotificationFragment.BOOLEAN_CODE);
        topNewsCheck = booleenArray[0];
        footballCheck = booleenArray[1];
        basketballCheck = booleenArray[2];
        baseballCheck = booleenArray[3];
        hockeyCheck = booleenArray[4];
        soccerCheck = booleenArray[5];

        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("Notifications", "onStartJob sent");

        context = getApplicationContext();
        setApiCall();

        return false;

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

//    public void retrofitLatestNews() {
//        Log.i("JobSchedulerService", "API call was made!");
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.nytimes.com/svc/news/v3/content/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        latestNewsService = retrofit.create(SearchAPI.class);
//
//        Call<NewsWireResults> call = latestNewsService.listNewsWireResults(chooseMagazineSource,
//                sections, 10);
//        call.enqueue(new Callback<NewsWireResults>() {
//            @Override
//            public void onResponse(Call<NewsWireResults> call, Response<NewsWireResults> response) {
//                newsWireResults = response.body();
//
//                articleLists = new ArrayList<>();
//                Collections.addAll(articleLists, newsWireResults.getResults());
//
//                if (newsWireResults == null) {
//                    return;
//                }
//                setNotifications();
//            }
//
//            @Override
//            public void onFailure(Call<NewsWireResults> call, Throwable t) {
//
//            }
//        });
//    }

    /**
     * this method sets notifications
     * when system makes api call
     */
    private void setNotifications() {

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_star_24dp);
        mBuilder.setContentTitle("Notification from Excalibur!");
        mBuilder.setContentText("New article: " + articleLists.get(0));
        mBuilder.setContentIntent(pIntent);
        mBuilder.setAutoCancel(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }


    private void createNotifications() {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_star_24dp);
        mBuilder.setContentTitle(sportsNewsList.get(0).getTitle());
        mBuilder.setContentText(sportsNewsList.get(0).getSubsection());
        mBuilder.setContentIntent(pIntent);
        mBuilder.setPriority(Notification.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }

    private void setApiCall(){
        Log.d("The Service",topNewsCheck +"");
        if (topNewsCheck){
           nytAllSportsNews();
        }
    }


    public void nytAllSportsNews() {

        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports", "Football");

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NytSportsResults>() {

                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "Completed!");
                        createNotifications();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(NytSportsResults nytSportsResults) {
                        Log.d("MainActivity", "Next!");


                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                    }
                });
    }

}

