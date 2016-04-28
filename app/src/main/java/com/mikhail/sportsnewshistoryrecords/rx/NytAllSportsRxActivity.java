package com.mikhail.sportsnewshistoryrecords.rx;

import android.os.Bundle;

import com.mikhail.sportsnewshistoryrecords.MainActivity;
import com.mikhail.sportsnewshistoryrecords.adaptors.ModelObjectAdaptor;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 4/27/16.
 */
public class NytAllSportsRxActivity extends MainActivity {

//    public static final String NYT_SOCCER = "Soccer";
//    public static final String NYT_FOOTBALL = "Pro Football";
//    public static final String NYT_BASEBALL = "Baseball";
//    public static final String NYT_PRO_BASKETBALL = "Pro Basketball";

    public static final String NYT_ALL = "Pro football,Pro basketball,baseball,soccer,";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports", NYT_ALL);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NytSportsResults>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(NytSportsResults nytSportsResults) {
                        ModelObjectAdaptor modelObjectAdapter = new ModelObjectAdaptor(nytSportsResults);
                        recyclerView.setAdapter(modelObjectAdapter);
                    }
                });

    }

}

