package com.mikhail.sportsnewshistoryrecords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;

/**
 * Created by Mikhail on 5/2/16.
 */
public class RecordsFragment extends Fragment {

    ImageView imageView;
    TextView textView;
    private int mFragmentType;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.records_layout, container, false);

        imageView = (ImageView) v.findViewById(R.id.league_records_logo);
        textView = (TextView) v.findViewById(R.id.league_records_text);


        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        setRecordsFragmentData();

    }

    public void setFragmentType(int type) {
        Log.d("RecordFragment","Fragmetn set!");
        mFragmentType = type;
        if (v != null) {
            setRecordsFragmentData();
        }
    }

    private void setRecordsFragmentData() {

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
                setSpanishSoccerRecords();
                break;
            case R.id.italian_soccer:
                setItalianSoccerRecords();
                break;
            case R.id.german_soccer:

                break;
        }
    }

    private void setItalianSoccerRecords() {

        imageView.setImageResource(R.drawable.serie_a_records_logo);
        textView.setText(getString(R.string.team_recors_title).toUpperCase() +
                "\n" + getString(R.string.serie_a_recors_team) +
                "\n" + getString(R.string.individual_records_title).toUpperCase() +
                "\n" + getString(R.string.serie_a_individual_records) +
                "\n" + getString(R.string.top_scorers_title).toUpperCase() +
                "\n" + getString(R.string.serie_a_scorers));
    }

    private void setSpanishSoccerRecords() {

        imageView.setImageResource(R.drawable.la_liga_records);
        textView.setText(getString(R.string.la_liga_records_title) + "\n" +
                getString(R.string.la_liga_records_team1) + "\n" +
                getString(R.string.la_liga_records_team2) + "\n" +
                getString(R.string.la_liga_records_team3) + "\n" +
                getString(R.string.la_liga_records_team4) + "\n" +
                getString(R.string.la_liga_records_team5) + "\n" +
                getString(R.string.la_liga_records_team6) + "\n" +
                getString(R.string.la_liga_records_team7) + "\n" +
                getString(R.string.la_liga_records_team8) + "\n" +
                getString(R.string.top_scorers_title).toUpperCase() + "\n" +
                getString(R.string.la_liga_scorers));
    }
}


