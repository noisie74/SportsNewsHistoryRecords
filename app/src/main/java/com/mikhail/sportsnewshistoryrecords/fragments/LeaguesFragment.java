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
import com.mikhail.sportsnewshistoryrecords.adapters.LeaguesNewsAdapter;
import com.mikhail.sportsnewshistoryrecords.api.NytSearchAPI;
import com.mikhail.sportsnewshistoryrecords.fragments.details_fragment.LeaguesDetailViewFragment;
import com.mikhail.sportsnewshistoryrecords.interfaces.LeaguesActivityControl;
import com.mikhail.sportsnewshistoryrecords.model.search.ArticleSearch;
import com.mikhail.sportsnewshistoryrecords.model.search.Doc;
import com.mikhail.sportsnewshistoryrecords.model.search.Multimedia;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mikhail.sportsnewshistoryrecords.util.CheckNetworkConnection.isConnected;

/**
 * Created by Mikhail on 4/28/16.
 */
public class LeaguesFragment extends Fragment {
    private int mFragmentType;
    public RecyclerView recyclerView;
    public ArrayList<Doc> searchSportsResults;
    protected SwipeRefreshLayout swipeContainer;
    protected Context context;
    public static final String NYT_HOCKEY = "Hockey";
    public static final String NYT_MLS = "MLS";
    public static final String NYT_FOOTBALL = "NFL Football";
    public static final String NYT_BASKETBALL = "NBA Basketball";
    public static final String NYT_BASEBALL = "Baseball";
    public static final String NYT_SPANISH = "Spanish soccer La liga";
    public static final String NYT_ENGLISH = "English Premier league";
    public static final String NYT_ITALIAN = "Italian Soccer Serie A";
    public static final String NYT_GERMAN = "Bundesliga";
    public LeaguesNewsAdapter leaguesNewsAdapter;
    public View v;
    public LeaguesActivityControl leaguesActivityControl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recycleview_activity_fragment, container, false);
        context = getContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

        searchSportsResults = new ArrayList<>();

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        setPullRefresh();

        leaguesNewsAdapter = new LeaguesNewsAdapter(searchSportsResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        leaguesFragmentSetOnItemClickListener();

        return v;
    }


    public void leaguesFragmentSetOnItemClickListener() {


        if (leaguesNewsAdapter != null) {
            leaguesNewsAdapter.setOnItemClickListener(new LeaguesNewsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    searchSportsResults.get(position);
                    String imageUrl;
                    imageUrl = "";

                    Multimedia[] multimedias = searchSportsResults.get(position).getMultimedia();
                    if (multimedias != null && multimedias.length > 0) {
                        imageUrl = multimedias[0].getUrl();
                    }
                    Bundle article = new Bundle(); //will bundle the 5 fields of articleSearchObjects in a string array
                    String[] articleDetails = {searchSportsResults.get(position).getHeadline().getMain(),
                            searchSportsResults.get(position).getWeb_url(),
                            imageUrl,
                            searchSportsResults.get(position).getLead_paragraph()};
                    article.putStringArray("searchedArticle", articleDetails);


                    leaguesActivityControl.showFragment(article);

                }
            });
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            leaguesActivityControl = (LeaguesActivityControl) getActivity();

        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    private void setPullRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isConnected(context)) {
                    Toast.makeText(getContext(), R.string.no_network, Toast.LENGTH_LONG).show();
                    swipeContainer.setRefreshing(false);
                } else {
                    apiCall();
                }
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.darker_gray,
                android.R.color.white);

    }

    private void nytSearchApiCall(String query) {

        NytSearchAPI.NytAPIRetrofitSimple nytSportsSearch = NytSearchAPI.create();

        Call<ArticleSearch> call = nytSportsSearch.response(query);

        call.enqueue(new Callback<ArticleSearch>() {
            @Override
            public void onResponse(Call<ArticleSearch> call, Response<ArticleSearch> response) {

                ArticleSearch nytSportsSearch = response.body();
                if (nytSportsSearch == null) {
                    return;
                }
                searchSportsResults.clear();
                Collections.addAll(searchSportsResults, nytSportsSearch.getResponse().getDocs());
                if (recyclerView != null) {
                    recyclerView.setAdapter(leaguesNewsAdapter);
                }
                swipeContainer.setRefreshing(false);


            }

            @Override
            public void onFailure(Call<ArticleSearch> call, Throwable t) {

            }
        });

    }


    public void setFragmentType(int type) {
        mFragmentType = type;
        apiCall();
    }


    private void apiCall() {
        switch (mFragmentType) {
            case R.id.nfl:
                nytSearchApiCall(NYT_FOOTBALL);
                break;
            case R.id.nba:
                nytSearchApiCall(NYT_BASKETBALL);
                break;
            case R.id.mlb:
                nytSearchApiCall(NYT_BASEBALL);
                break;
            case R.id.nhl:
                nytSearchApiCall(NYT_HOCKEY);
                break;
            case R.id.mls:
                nytSearchApiCall(NYT_MLS);
                break;
            case R.id.english_soccer:
                nytSearchApiCall(NYT_ENGLISH);
                break;
            case R.id.spanish_soccer:
                nytSearchApiCall(NYT_SPANISH);
                break;
            case R.id.italian_soccer:
                nytSearchApiCall(NYT_ITALIAN);
                break;
            case R.id.german_soccer:
                nytSearchApiCall(NYT_GERMAN);
                break;
        }
    }

}

