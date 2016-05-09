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
import com.squareup.picasso.Picasso;

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
        mFragmentType = type;
        if (v != null) {
            setRecordsFragmentData();
        }
    }

    private void setRecordsFragmentData() {

        switch (mFragmentType) {
            case R.id.nfl:
                setNFLRecords();
                break;
            case R.id.nba:
                setNBARecords();
                break;
            case R.id.mlb:
                setMLBRecords();
                break;
            case R.id.nhl:
                setNHLRecords();
                break;
            case R.id.mls:
                setMLSSoccerRecords();
                break;
            case R.id.english_soccer:
                setEnglishSoccerRecords();
                break;
            case R.id.spanish_soccer:
                setSpanishSoccerRecords();
                break;
            case R.id.italian_soccer:
                setItalianSoccerRecords();
                break;
            case R.id.german_soccer:
                setGermanSoccerRecords();
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

        imageView.setImageResource(R.drawable.laliga_records);
        textView.setText(getString(R.string.la_liga_records_title).toUpperCase() + "\n" +
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

    private void setEnglishSoccerRecords() {

        imageView.setImageResource(R.drawable.premier_league_logo);
        textView.setText(getString(R.string.english_records_title1).toUpperCase() + "\n" +
                getString(R.string.english_records_title2) + "\n" +
                getString(R.string.english_records1) + "\n" +
                getString(R.string.english_records_title3) + "\n" +
                getString(R.string.english_records2) + "\n" +
                getString(R.string.english_records_title4) + "\n" +
                getString(R.string.english_records3) + "\n" +
                getString(R.string.english_history_title5) + "\n" +
                getString(R.string.english_records4) + "\n" +
                getString(R.string.english_history_title6) + "\n" +
                getString(R.string.english_records5) + "\n" +
                getString(R.string.english_history_title7) + "\n" +
                getString(R.string.english_records6) + "\n" +
                getString(R.string.english_history_title8) + "\n" +
                getString(R.string.english_records7) + "\n" +
                getString(R.string.english_records8) + "\n" +
                getString(R.string.english_records6) + "\n" +
                getString(R.string.english_history_title9) + "\n" +
                getString(R.string.individual_records_title) + "\n" +
                getString(R.string.english_records9) + "\n" +
                getString(R.string.top_scorers_title).toUpperCase() + "\n" +
                getString(R.string.english_records10));
    }

    private void setGermanSoccerRecords() {

        imageView.setImageResource(R.drawable.logo_german);
        textView.setText(getString(R.string.german_records_title1).toUpperCase() + "\n" +
                getString(R.string.german_records_title2) + "\n" +
                getString(R.string.german_records1) + "\n" +
                getString(R.string.german_records_title3) + "\n" +
                getString(R.string.german_records2) + "\n" +
                getString(R.string.german_records_title4) + "\n" +
                getString(R.string.german_records3) + "\n" +
                getString(R.string.german_records_title5) + "\n" +
                getString(R.string.german_records4) + "\n" +
                getString(R.string.german_records_title6) + "\n" +
                getString(R.string.german_records5) + "\n" +
                getString(R.string.german_records_title7) + "\n" +
                getString(R.string.german_records_title8) + "\n" +
                getString(R.string.german_records6) + "\n" +
                getString(R.string.german_records_title9) + "\n" +
                getString(R.string.german_records7) + "\n" +
                getString(R.string.top_scorers_title).toUpperCase() + "\n" +
                getString(R.string.german_records_10));
    }

    private void setMLSSoccerRecords() {

        imageView.setImageResource(R.drawable.mls_logo);
        textView.setText(getString(R.string.top_scorers_title).toUpperCase() + "\n" +
                getString(R.string.mls_records));
    }

    private void setNBARecords() {

        imageView.setImageResource(R.drawable.nba_logo_history);
        textView.setText(getString(R.string.nba_records_title).toUpperCase() + "\n" +
                getString(R.string.nba_records)
                + "\n" + getString(R.string.nba_records1)
                + "\n" + getString(R.string.nba_records3)
                + "\n" + getString(R.string.nba_records4)
                + "\n" + getString(R.string.nba_records5) );
    }

    private void setNFLRecords() {

        imageView.setImageResource(R.drawable.nfl_l);
        textView.setText(getString(R.string.nfl_records_title1).toUpperCase() + "\n" +
                getString(R.string.nfl_records1) + "\n"
                + getString(R.string.nfl_records_title2)
                + "\n" + getString(R.string.nfl_records2)
                + "\n" + getString(R.string.nfl_records_title3)
                + "\n" + getString(R.string.nfl_records3)
                + "\n" + getString(R.string.nfl_records_title4)
                + "\n" + getString(R.string.nfl_records4)
                + "\n" + getString(R.string.nfl_records_title5)
                + "\n" + getString(R.string.nfl_records5));
    }

    private void setMLBRecords() {

        imageView.setImageResource(R.drawable.mlb_l);
        textView.setText(getString(R.string.mlb_records_title).toUpperCase() + "\n" +
                getString(R.string.mlb_records) + "\n"
                + getString(R.string.mlb_records_title1)
                + "\n" + getString(R.string.mlb_records1)
                + "\n" + getString(R.string.mlb_records_title2)
                + "\n" + getString(R.string.mlb_records2)
                + "\n" + getString(R.string.mlb_records_title3)
                + "\n" + getString(R.string.mlb_records3)
                + "\n" + getString(R.string.mlb_records_title4)
                + "\n" + getString(R.string.mlb_records4)
                + "\n" + getString(R.string.mlb_records_title5)
                + "\n" + getString(R.string.mlb_records5));
    }

    private void setNHLRecords() {

        imageView.setImageResource(R.drawable.nhl_logo);
        textView.setText(getString(R.string.nhl_records_title).toUpperCase() + "\n" +
                getString(R.string.nhl_records_title1) + "\n"
                + getString(R.string.nhl_records)
                + "\n" + getString(R.string.nhl_records_title2)
                + "\n" + getString(R.string.nhl_records_title3)
                + "\n" + getString(R.string.nhl_records2)
                + "\n" + getString(R.string.nhl_records_title4)
                + "\n" + getString(R.string.nhl_records3)
                + "\n" + getString(R.string.nhl_records_title5)
                + "\n" + getString(R.string.nhl_records4)
                + "\n" + getString(R.string.nhl_records_title6)
                + "\n" + getString(R.string.nhl_records5)
                + "\n" + getString(R.string.nhl_records_title7)
                + "\n" + getString(R.string.nhl_records6)
                + "\n" + getString(R.string.nhl_records_title8)
                + "\n" + getString(R.string.nhl_records7));
    }
}
