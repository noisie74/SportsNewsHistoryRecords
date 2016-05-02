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

    HistoryAdapter historyAdapter;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.history_layout, container, false);

        imageView  = (ImageView) v.findViewById(R.id.league_logo);
        textView = (TextView) v.findViewById(R.id.league_history_text);
        imageView.setImageResource(R.drawable.serie_a_history);
        textView.setText("Serie A (Italian pronunciation: [ˈsɛːrje ˈa]), also called Serie A TIM due to sponsorship by TIM, is a professional league competition for football clubs located at the top of the Italian football league system and has been operating for over eighty years since the 1929–30 season. It had been organized by Lega Calcio until 2010, but a new league, the Lega Serie A, was created for the 2010–11 season. Serie A is regarded as one of the best football leagues in the world and it is often depicted as the most tactical national league. Serie A is the world's second-strongest national league according to IFFHS and has produced the highest number of European Cup finalists: Italian clubs have reached the final of the competition on a record 26 different occasions, winning the title 12 times. Serie A is ranked fourth among European leagues according to UEFA's league coefficient, behind La Liga, Bundesliga and Premier League, which is based on the performance of Italian clubs in the Champions League and the Europa League during the last five years. Serie A led the UEFA ranking from 1986 to 1988 and from 1990 to 1999. \n" +
                "In its current format, the Italian Football Championship was revised from having regional and interregional rounds, to a single-tier league from the 1929–30 season onwards. The championship titles won prior to 1929 are officially recognised by FIGC with the same weighting as titles that were subsequently awarded. However, the 1945–46 season, when the league was played over two geographical groups due to the ravages of WWII, is not statistically considered, even if its title is fully official.[6] All the winning teams are recognised with the title of Campione d'Italia(\"Champion of Italy\"), which is ratified by the Lega Serie A before the start of the next edition of the championship.\n" +
                "The league hosts three of the world's most famous clubs as Juventus, Milan and Internazionale, all founding members of the G-14, a group which represented the largest and most prestigious European football clubs; Serie A was the only league to produce three founding members.[7] More players have won the coveted Ballon d'Or award while playing at a Serie A club than any other league in the world. - ahead of Spain's La Liga, although the actual number of Ballon d'Or won by players in these two leagues is equal at 18 each if including the FIFA Ballon d'Or. Milan is the second club with the most official international titles in the world, with 18.[9] Juventus, Italy's most successful club of the 20th century and the most successful Italian team,[11] is tied for fourth in Europe and eighth in the world in the same ranking.[12] The club is the only one in the world to have won all possible official continental competitions and the world title. Internazionale, following their achievements in the 2009–10 season, became the first Italian team to have achieved a treble. Juventus, Milan and Inter, along with Roma, Fiorentina,Lazio and Napoli, are known as the Seven Sisters of Italian football.\n");


        return v;

    }
}
