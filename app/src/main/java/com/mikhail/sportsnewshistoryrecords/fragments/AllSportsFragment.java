package com.mikhail.sportsnewshistoryrecords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.adapters.AllSportsAdapter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;

import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 4/27/16.
 */
public class AllSportsFragment extends Fragment {

    private int mFragmentType;
    public RecyclerView recyclerView;
    public AllSportsAdapter allSportsAdapter;
    public ArrayList<NytSportsObjects> sportsNewsList;
    public SwipeRefreshLayout swipeContainer;
    public static final String NYT_ALL = "Pro football,Pro basketball,baseball,soccer,Hockey";
    public static final String NYT_FOOTBALL = "Pro%20Football";
    public static final String NYT_BASKETBALL = "Pro basketball";
    public static final String NYT_BASEBALL = "baseball";
    public static final String NYT_HOCKEY = "Hockey";
    public static final String NYT_SOCCER = "Soccer";
    NytSportsResults nytSportsResults;
    private View rootView;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recycleview_activity_fragment, container, false);

        setViews(rootView);
        sportsNewsList = new ArrayList<>();
        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        setPullRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allSportsAdapterItemClicker();

        return rootView;
    }

    public void setFragment(int type){
        this.mFragmentType = type;
        nytAllSportsNews();
    }

    /**
     * Set the itemClicker for the recycleView
     */
    private void allSportsAdapterItemClicker() {
        allSportsAdapter.setOnItemClickListener(new AllSportsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                sportsNewsList.get(position);

                Bundle article = new Bundle(); //will bundle the 5 fields of NYTSportsObjects in a string array
                String[] articleDetails = {sportsNewsList.get(position).getSection(),
                        sportsNewsList.get(position).getTitle(),
                        sportsNewsList.get(position).getUrl(),
                        sportsNewsList.get(position).getThumbnail_standard(),
                        sportsNewsList.get(position).getAbstractResult()};
                article.putStringArray("article", articleDetails);

                Fragment newsDetailsFragment = new NewsDetailsFragment();
                newsDetailsFragment.setArguments(article);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_container, newsDetailsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    /**
     * this re-run the api call to check new articles
     */
    private void setPullRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nytAllSportsNews();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.darker_gray,
                android.R.color.white);

    }

    /**
     * this will pull a list of articles according to the navi bar topics
     * default will pull all topics
     */
    public void nytAllSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports", NYT_ALL);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NytSportsResults>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "Completed!");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(NytSportsResults nytSportsResults) {
                        Log.d("MainActivity", "Next!");

                        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                        recyclerView.setAdapter(allSportsAdapter);
                    }
                });
    }

    public void nytSoccerSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports",NYT_SOCCER);

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
                        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                        recyclerView.setAdapter(allSportsAdapter);
                    }
                });
    }

    public void nytFootballSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports",NYT_FOOTBALL);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NytSportsResults>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(NytSportsResults nytSportsResults) {
                        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                        recyclerView.setAdapter(allSportsAdapter);
                    }
                });
    }

    public void nytBaseballSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports",NYT_BASEBALL);

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
                        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                        recyclerView.setAdapter(allSportsAdapter);
                    }
                });
    }

    public void nytBasketballSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports",NYT_BASKETBALL);

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
                        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                        recyclerView.setAdapter(allSportsAdapter);
                    }
                });
    }

    public void nytHockeySportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults("all", "sports",NYT_HOCKEY);

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
                        allSportsAdapter = new AllSportsAdapter(nytSportsResults);
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                        recyclerView.setAdapter(allSportsAdapter);
                    }
                });
    }

    public void setViews(View v) {

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

    }

}

