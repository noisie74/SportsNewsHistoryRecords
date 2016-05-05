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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.history_layout, container, false);

        imageView = (ImageView) v.findViewById(R.id.league_logo);
        textView = (TextView) v.findViewById(R.id.league_history_text);
        imageView.setImageResource(R.drawable.serie_a_history);
        textView.setText(getString(R.string.serie_a_history1)+
                "\n" + getString(R.string.serie_a_history2) + "\n" +
                getString(R.string.serie_a_history3) +"\n" +
                getString(R.string.serie_a_history4));
        return v;

    }
}
