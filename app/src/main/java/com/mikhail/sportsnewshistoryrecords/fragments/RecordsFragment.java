package com.mikhail.sportsnewshistoryrecords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.records_layout, container, false);

        imageView = (ImageView) v.findViewById(R.id.league_records_logo);
        textView = (TextView) v.findViewById(R.id.league_records_text);
        imageView.setImageResource(R.drawable.serie_a_records_logo);
        textView.setText(getString(R.string.team_recors_title).toUpperCase() +
                "\n" + getString(R.string.serie_a_recors_team) +
                "\n" + getString(R.string.individual_records_title).toUpperCase() +
                "\n" + getString(R.string.serie_a_individual_records) +
                "\n" + getString(R.string.top_scorers_title).toUpperCase() +
                "\n" + getString(R.string.serie_a_scorers));

        return v;

    }
}
