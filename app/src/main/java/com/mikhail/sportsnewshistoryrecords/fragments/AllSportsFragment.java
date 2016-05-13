package com.mikhail.sportsnewshistoryrecords.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.activities.MainActivity;
import com.mikhail.sportsnewshistoryrecords.adapters.AllSportsAdapter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.interfaces.MainActivityControlAllSports;
import com.mikhail.sportsnewshistoryrecords.model.newswire.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.newswire.NytSportsResults;
import com.mikhail.sportsnewshistoryrecords.util.CheckNetworkConnection;

import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static com.mikhail.sportsnewshistoryrecords.util.CheckNetworkConnection.*;

/**
 * Fragment with api calls for NYT News Wire
 */
public class AllSportsFragment extends Fragment {

    public int mFragmentType;
    public RecyclerView recyclerView;
    public AllSportsAdapter allSportsAdapter;
    public ArrayList<NytSportsObjects> sportsNewsList;
    public SwipeRefreshLayout swipeContainer;
    public static final String NYT_SOURCE = "all";
    public static final String NYT_SUBSECTION = "sports";
    public static final String NYT_ALL = "Pro football,Pro basketball,baseball,soccer,Hockey";
    public static final String NYT_FOOTBALL = "Pro%20Football";
    public static final String NYT_BASKETBALL = "Pro basketball";
    public static final String NYT_BASEBALL = "baseball";
    public static final String NYT_HOCKEY = "Hockey";
    public static final String NYT_SOCCER = "Soccer";
    private View rootView;
    private boolean recyclerViewIsSet = false;
    private MainActivityControlAllSports mainActivityControl;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recycleview_activity_fragment, container, false);
        context = getContext();
        setViews(rootView);
        sportsNewsList = new ArrayList<>();
        allSportsAdapter = new AllSportsAdapter();
        setPullRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allSportsAdapterItemClicker();

        return rootView;
    }

    public void setFragment(int type) {
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

                Bundle article = new Bundle(); //will bundle the 5 fields of NYTSportsObjects in a string array
                String[] articleDetails = {sportsNewsList.get(position).getSection(),
                        sportsNewsList.get(position).getTitle(),
                        sportsNewsList.get(position).getUrl(),
                        sportsNewsList.get(position).getThumbnail_standard(),
                        sportsNewsList.get(position).getAbstractResult()};
                article.putStringArray("article", articleDetails);

                mainActivityControl.setBundle(article);

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mainActivityControl = (MainActivityControlAllSports) getActivity();

        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    /**
     * this re-run the api call to check new articles
     */
    private void setPullRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isConnected(context)) {
                    Toast.makeText(getContext(), R.string.no_network, Toast.LENGTH_LONG).show();
                    swipeContainer.setRefreshing(false);
                } else {
                    apiForSpinnerPosition();
                }

            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.darker_gray,
                android.R.color.white);

    }

    /**
     * check current spinner position and make correct api call
     */
    private void apiForSpinnerPosition() {
        if (mainActivityControl.getSpinnerSelection() == 0) {
            nytAllSportsNews();
            Timber.d("All Sports pullRefresh");
        } else if (mainActivityControl.getSpinnerSelection() == 1) {
            nytFootballSportsNews();
            Timber.d("Football pullRefresh");
        } else if (mainActivityControl.getSpinnerSelection() == 2) {
            nytBasketballSportsNews();
            Timber.d("Basketball pullRefresh");
        } else if (mainActivityControl.getSpinnerSelection() == 3) {
            nytApiCall(NYT_BASEBALL);
            Timber.d("Baseball pullRefresh");
        } else if (mainActivityControl.getSpinnerSelection() == 4) {
            nytHockeySportsNews();
            Timber.d("Hockey pullRefresh");
        } else if (mainActivityControl.getSpinnerSelection() == 5) {
            nytSoccerSportsNews();
            Timber.d("Soccer pullRefresh");
        }
    }

    /**
     * this will pull a list of articles according to the navi bar topics
     * default will pull all topics
     */
    public void nytAllSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOURCE, NYT_SUBSECTION, NYT_ALL);

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
                        Timber.i("Completed");
                        if (recyclerViewIsSet) {
                            allSportsAdapter.updateData(nytSportsResults);
                        } else {
                            allSportsAdapter.updateData(nytSportsResults);
                            recyclerView.setAdapter(allSportsAdapter);
                            recyclerViewIsSet = true;
                        }
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                    }
                });

    }

    public void nytSoccerSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOURCE, NYT_SUBSECTION, NYT_SOCCER);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NytSportsResults>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(NytSportsResults nytSportsResults) {
                        allSportsAdapter.updateData(nytSportsResults);
                        sportsNewsList.clear();
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                    }
                });
    }

    public void nytFootballSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOURCE, NYT_SUBSECTION, NYT_FOOTBALL);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NytSportsResults>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(NytSportsResults nytSportsResults) {

                        allSportsAdapter.updateData(nytSportsResults);
                        sportsNewsList.clear();
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                    }
                });
    }

    public void nytApiCall(String query) {

        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOURCE, NYT_SUBSECTION, query);

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
                        allSportsAdapter.updateData(nytSportsResults);
                        sportsNewsList.clear();
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                    }
                });

    }

    public void nytBaseballSportsNews() {

    }

    public void nytBasketballSportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOURCE, NYT_SUBSECTION, NYT_BASKETBALL);

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
                        allSportsAdapter.updateData(nytSportsResults);
                        sportsNewsList.clear();
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                    }
                });
    }

    public void nytHockeySportsNews() {
        NytAPI.NytRx nytSports = NytAPI.createRx();

        Observable<NytSportsResults> observable = nytSports.nytSportsResults(NYT_SOURCE, NYT_SUBSECTION, NYT_HOCKEY);

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
                        allSportsAdapter.updateData(nytSportsResults);
                        sportsNewsList.clear();
                        Collections.addAll(sportsNewsList, nytSportsResults.getResults());
                        swipeContainer.setRefreshing(false);
                    }
                });
    }

    public void setViews(View v) {
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

    }

}

