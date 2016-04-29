package com.mikhail.sportsnewshistoryrecords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.adapters.AllSportsAdapter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mikhail on 4/27/16.
 */
public class AllSportsFragment extends Fragment {

    public final static String TAG = "ArticleRecycleView";
    public static RecyclerView recyclerView;
    public ArrayList<NytSportsObjects> breakingNewsLists;
    protected SwipeRefreshLayout swipeContainer;
    public static final String NYT_ALL = "Pro football,Pro basketball,baseball,soccer,";
    public static final String NYT_SOCCER = "Soccer";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycleview_activity_fragment, container, false);

        setViews(v);
        breakingNewsLists = new ArrayList<>();
//        recycleAdapter = new NewsRecyclerAdapter(breakingNewsLists);
        swipeContainer = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
        setPullRefresh();
//        nytAllSportsNews();
//        nytSoccerSportsNews();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycleAdapterItemClicker();

        return v;
    }

    /**
     * Set the itemClicker for the recycleView
     */
//    private void recycleAdapterItemClicker(){
//        recycleAdapter.setOnItemClickListener(new NewsRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//                Log.i(TAG, String.valueOf(position));
//                breakingNewsLists.get(position);
//
//                Bundle article = new Bundle(); //will bundle the 5 fields of newsWireObjects in a string array
//                String[] articleDetails = {breakingNewsLists.get(position).getSection(),
//                        breakingNewsLists.get(position).getTitle(),
//                        breakingNewsLists.get(position).getUrl(),
//                        breakingNewsLists.get(position).getThumbnail_standard(),
//                        breakingNewsLists.get(position).getAbstractResult()};
//                article.putStringArray("article", articleDetails);
//
//                Fragment articleStory = new ArticleStory();
//                articleStory.setArguments(article);
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.frag_container, articleStory);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//    }

    /**
     * this re-run the api call to check new articles
     */
    private void setPullRefresh(){
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
    public static void nytAllSportsNews() {
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
                        AllSportsAdapter modelObjectAdapter = new AllSportsAdapter(nytSportsResults);
                        recyclerView.setAdapter(modelObjectAdapter);
                    }
                });
    }

    public static void nytSoccerSportsNews() {
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
                        AllSportsAdapter modelObjectAdapter = new AllSportsAdapter(nytSportsResults);
                        recyclerView.setAdapter(modelObjectAdapter);
                    }
                });
    }

    public void setViews(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

    }
}
