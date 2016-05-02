package com.mikhail.sportsnewshistoryrecords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.adapters.LeaguesNewsAdapter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.api.NytSearchAPI;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.search.ArticleSearch;
import com.mikhail.sportsnewshistoryrecords.model.search.Doc;
import com.mikhail.sportsnewshistoryrecords.model.search.Response;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 4/28/16.
 */
public class LeaguesFragment extends Fragment {

    public static RecyclerView recyclerView;
    public ArrayList<Doc> searchSportsResults;
    protected SwipeRefreshLayout swipeContainer;
    public static final String NYT_ITALIAN = "Italian Serie A";
    public static final String NYT_MLS = "MLS";
    public static final String NYT_GERMAN = "Bundesliga";
    public static final String NYT_FOOTBALL = "NFL Football";
    public static final String NYT_BASKETBALL = "NBA Basketball";





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycleview_activity_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);
        searchSportsResults = new ArrayList<>();
        swipeContainer = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
        setPullRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    private void setPullRefresh(){
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serieASearch();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.darker_gray,
                android.R.color.white);

    }

    public static void serieASearch() {
        NytSearchAPI.NytRx nytSports = NytSearchAPI.createRx();

        Observable<ArticleSearch> observable = nytSports.response(NYT_ITALIAN);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArticleSearch>() {
                    @Override
                    public void onCompleted() {

                        Log.d("LeaguesFragment", "Query Succes!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LeaguesFragment", "Error!");
                    }

                    @Override
                    public void onNext(ArticleSearch response) {
                        LeaguesNewsAdapter leaguesNewsAdapter = new LeaguesNewsAdapter(response);
                        recyclerView.setAdapter(leaguesNewsAdapter);
                    }
                });
    }

    public static void mlsSearch() {
        NytSearchAPI.NytRx nytSports = NytSearchAPI.createRx();

        Observable<ArticleSearch> observable = nytSports.response(NYT_MLS);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArticleSearch>() {
                    @Override
                    public void onCompleted() {

                        Log.d("LeaguesFragment", "Query Succes!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LeaguesFragment", "Error!");
                    }

                    @Override
                    public void onNext(ArticleSearch response) {
                        LeaguesNewsAdapter leaguesNewsAdapter = new LeaguesNewsAdapter(response);
                        recyclerView.setAdapter(leaguesNewsAdapter);
                    }
                });
    }

    public static void bundesligaSearch() {
        NytSearchAPI.NytRx nytSports = NytSearchAPI.createRx();

        Observable<ArticleSearch> observable = nytSports.response(NYT_GERMAN);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArticleSearch>() {
                    @Override
                    public void onCompleted() {

                        Log.d("LeaguesFragment", "Query Succes!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LeaguesFragment", "Error!");
                    }

                    @Override
                    public void onNext(ArticleSearch response) {
                        LeaguesNewsAdapter leaguesNewsAdapter = new LeaguesNewsAdapter(response);
                        recyclerView.setAdapter(leaguesNewsAdapter);
                    }
                });
    }

    public static void nbaSearch() {
        NytSearchAPI.NytRx nytSports = NytSearchAPI.createRx();

        Observable<ArticleSearch> observable = nytSports.response(NYT_BASKETBALL);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArticleSearch>() {
                    @Override
                    public void onCompleted() {

                        Log.d("LeaguesFragment", "Query Succes!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LeaguesFragment", "Error!");
                    }

                    @Override
                    public void onNext(ArticleSearch response) {
                        LeaguesNewsAdapter leaguesNewsAdapter = new LeaguesNewsAdapter(response);
                        recyclerView.setAdapter(leaguesNewsAdapter);
                    }
                });
    }

    public static void footballSearch() {
        NytSearchAPI.NytRx nytSports = NytSearchAPI.createRx();

        Observable<ArticleSearch> observable = nytSports.response(NYT_FOOTBALL);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArticleSearch>() {
                    @Override
                    public void onCompleted() {

                        Log.d("LeaguesFragment", "Query Succes!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LeaguesFragment", "Error!");
                    }

                    @Override
                    public void onNext(ArticleSearch response) {
                        LeaguesNewsAdapter leaguesNewsAdapter = new LeaguesNewsAdapter(response);
                        recyclerView.setAdapter(leaguesNewsAdapter);
                    }
                });
    }
}
