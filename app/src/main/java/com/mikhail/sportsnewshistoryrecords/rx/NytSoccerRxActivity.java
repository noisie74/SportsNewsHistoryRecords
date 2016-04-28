package com.mikhail.sportsnewshistoryrecords.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mikhail.sportsnewshistoryrecords.MainActivity;
import com.mikhail.sportsnewshistoryrecords.adaptors.ModelObjectAdaptor;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 4/27/16.
 */
public class NytSoccerRxActivity extends MainActivity {

    public static final String NYT_SOCCER = "Soccer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOCCER);

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
