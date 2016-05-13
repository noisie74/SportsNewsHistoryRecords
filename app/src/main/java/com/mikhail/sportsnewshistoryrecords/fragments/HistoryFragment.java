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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mikhail.sportsnewshistoryrecords.R;

import java.util.ArrayList;

/**
 * Created by Mikhail on 5/2/16.
 */
public class HistoryFragment extends Fragment {

    ImageView imageView;
    TextView textView;
    private int mFragmentType;
    private View v;
    Firebase firebaseRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.history_layout, container, false);
        imageView = (ImageView) v.findViewById(R.id.league_logo);
        textView = (TextView) v.findViewById(R.id.league_history_text);
        firebaseRef = new Firebase("https://sportsnewsstatsrec.firebaseio.com/");
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setHistoryFragmentData();
    }

    public void setFragmentType(int type) {
        mFragmentType = type;
        if (v != null) {
            setHistoryFragmentData();

        }
    }

    private void setHistoryFragmentData() {

        switch (mFragmentType) {
            case R.id.nfl:
                setNFLHistory();
                break;
            case R.id.nba:
                setNBAHistory();
                break;
            case R.id.mlb:
                setMLBHistory();
                break;
            case R.id.nhl:
                setNHLHistory();
                break;
            case R.id.mls:
                setMLSSoccerHistory();
                break;
            case R.id.english_soccer:
                setEnglishSoccerHistory();
                break;
            case R.id.spanish_soccer:
                setSpanishSoccerHistory();
                break;
            case R.id.italian_soccer:
                setItalianSoccerHistory();
                break;
            case R.id.german_soccer:
                setGermanSoccerHistory();
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

    private void setEnglishSoccerHistory() {

        imageView.setImageResource(R.drawable.english_pm_logo);
        textView.setText(getString(R.string.english_history1) +
                "\n" + getString(R.string.english_history_title1).toUpperCase() + "\n" +
                getString(R.string.english_history_title2) + "\n" +
                getString(R.string.english_history2) + "\n" +
                getString(R.string.english_history3) + "\n" +
                getString(R.string.english_history4) + "\n" +
                getString(R.string.english_history5));
    }

    private void setGermanSoccerHistory() {

        imageView.setImageResource(R.drawable.logo_german);
        textView.setText(getString(R.string.german_history_1) + "\n" + "\n" +
                getString(R.string.german_history_title1).toUpperCase() + "\n" +
                getString(R.string.german_history_title2) + "\n" +
                getString(R.string.german_history_2) + "\n" +
                getString(R.string.german_history_title3) + "\n" +
                getString(R.string.german_history_3));
    }

    private void setMLSSoccerHistory() {

        imageView.setImageResource(R.drawable.mls);
        textView.setText(getString(R.string.mls_history1) + "\n" +
                getString(R.string.mls_history4) + "\n" +
                getString(R.string.mls_title1).toUpperCase() + "\n" +
                getString(R.string.mls_history2) + "\n" +
                getString(R.string.mls_title2) + "\n" +
                getString(R.string.mls_history3));
    }

    private void setNBAHistory() {
        imageView.setImageResource(R.drawable.nba_logo_history);

        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s1 = dataSnapshot.child("nba3").getValue(String.class);
                String s2 = dataSnapshot.child("nba1").getValue(String.class);
                String s3 = dataSnapshot.child("nba2").getValue(String.class);
                String s4 = dataSnapshot.child("nba12").getValue(String.class);
                String s5 = dataSnapshot.child("nba4").getValue(String.class);
                String s6 = dataSnapshot.child("nba5").getValue(String.class);
                String s7 = dataSnapshot.child("nba6").getValue(String.class);
                String s8 = dataSnapshot.child("nba7").getValue(String.class);
                String s9 = dataSnapshot.child("nba8").getValue(String.class);
                String s10 = dataSnapshot.child("nba9").getValue(String.class);
                String s11 = dataSnapshot.child("nba10").getValue(String.class);
                String s12 = dataSnapshot.child("nba11").getValue(String.class);

                textView.setText(s4 + "\n" + "\n" + s2 + "\n" + "\n" +
                        s3 + "\n" + "\n" + s1 + "\n" + "\n" + s5 + "\n"
                        + "\n" + s6 + "\n" + "\n" + s7 + "\n" + "\n" + s8
                        + "\n" + "\n" + s9 + "\n" + "\n" + s10 + "\n" + "\n"
                        + s11 + "\n" + "\n" + s12);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void setNFLHistory() {
        imageView.setImageResource(R.drawable.nfl_logo);

        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s1 = dataSnapshot.child("nfl1").getValue(String.class);
                String s2 = dataSnapshot.child("nfl2").getValue(String.class);
                String s3 = dataSnapshot.child("nfl3").getValue(String.class);
                String s4 = dataSnapshot.child("nfl4").getValue(String.class);
                String s5 = dataSnapshot.child("nfl5").getValue(String.class);
                String s6 = dataSnapshot.child("nfl6").getValue(String.class);
                String s7 = dataSnapshot.child("nfl7").getValue(String.class);
                String s8 = dataSnapshot.child("nfl8").getValue(String.class);
                String s9 = dataSnapshot.child("nfl9").getValue(String.class);

                textView.setText(s1 + "\n" + "\n" + s2 + "\n" + "\n" +
                        s3 + "\n" + "\n" + s4 + "\n" + "\n" + s5 + "\n"
                        + "\n" + s6 + "\n" + "\n" + s7 + "\n" + "\n" + s8
                        + "\n" + "\n" + s9);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void setNHLHistory() {
        imageView.setImageResource(R.drawable.nhl_l);

        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s1 = dataSnapshot.child("nhl1").getValue(String.class);
                String s2 = dataSnapshot.child("nhl2").getValue(String.class);

                textView.setText(s1 + "\n" + "\n" + s2);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void setMLBHistory() {
        imageView.setImageResource(R.drawable.mlb);

        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s1 = dataSnapshot.child("mlb1").getValue(String.class);
                String s2 = dataSnapshot.child("mlb2").getValue(String.class);
                String s3 = dataSnapshot.child("mlb3").getValue(String.class);

                textView.setText(s1 + "\n" + "\n" + s2 + "\n" + "\n" + s3);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}



