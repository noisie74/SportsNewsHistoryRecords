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
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.adapters.HistoryAdapter;

import java.util.ArrayList;

/**
 * Created by Mikhail on 5/2/16.
 */
public class HistoryFragment extends Fragment {

    ImageView imageView;
    TextView textView;
    private int mFragmentType;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.history_layout, container, false);
        imageView = (ImageView) v.findViewById(R.id.league_logo);
        textView = (TextView) v.findViewById(R.id.league_history_text);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setHistoryFragmentData();

    }


    public void setFragmentType(int type) {
        mFragmentType = type;
        if (v != null){
            setHistoryFragmentData();

        }
    }

    private void setHistoryFragmentData() {

        switch (mFragmentType) {
            case R.id.nfl:
                break;
            case R.id.nba:
                break;
            case R.id.mlb:
                break;
            case R.id.nhl:
                break;
            case R.id.mls:
                break;
            case R.id.english_soccer:
                break;
            case R.id.spanish_soccer:
                setSpanishSoccerHistory();
                break;
            case R.id.italian_soccer:
                setItalianSoccerHistory();
                break;
            case R.id.german_soccer:
                break;
        }
    }

    private void setItalianSoccerHistory() {

        imageView.setImageResource(R.drawable.serie_a_history);
        textView.setText(getString(R.string.serie_a_history1) +
                "\n" + getString(R.string.serie_a_history2) + "\n" +
                getString(R.string.serie_a_history3) + "\n" +
                getString(R.string.serie_a_history4));
    }

    private void setSpanishSoccerHistory() {

        imageView.setImageResource(R.drawable.laliga_logo);
        textView.setText(getString(R.string.la_liga_history) +
                "\n" + getString(R.string.la_liga_foundation_title) + "\n" +
                getString(R.string.la_liga_foundation) + "\n" +
                getString(R.string.la_liga_title1) + "\n" +
                getString(R.string.la_liga_30) + "\n" +
                getString(R.string.la_liga_title2) + "\n" +
                getString(R.string.la_liga_40) + "\n" +
                getString(R.string.la_liga_title3) + "\n" +
                getString(R.string.la_liga_50) + "\n" +
                getString(R.string.la_liga_title4) + "\n" +
                getString(R.string.la_liga_60) + "\n" +
                getString(R.string.la_liga_title5) + "\n" +
                getString(R.string.la_liga_title6) + "\n" +
                getString(R.string.la_liga_90) + "\n" +
                getString(R.string.la_liga_title7) + "\n" +
                getString(R.string.la_liga_00) + "\n" +
                getString(R.string.la_liga_title8) + "\n" +
                getString(R.string.la_liga_10) + "\n");
    }
}


