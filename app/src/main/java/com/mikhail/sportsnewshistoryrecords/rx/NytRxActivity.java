package com.mikhail.sportsnewshistoryrecords.rx;

import android.os.Bundle;

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
public class NytRxActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<List<NytSportsResults>> observable = nytSports.nytSportsResults("all", "sports", "Soccer");

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NytSportsResults>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(List<NytSportsResults> nytSportsResults) {
                        ModelObjectAdaptor modelObjectAdapter = new ModelObjectAdaptor(nytSportsResults);
                        recyclerView.setAdapter(modelObjectAdapter);
                    }
                });

    }

}

