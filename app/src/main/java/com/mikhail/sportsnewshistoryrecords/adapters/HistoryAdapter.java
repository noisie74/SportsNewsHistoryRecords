package com.mikhail.sportsnewshistoryrecords.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.mikhail.sportsnewshistoryrecords.R;

/**
 * Created by Mikhail on 5/1/16.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView leagueLogoImage;
        public ListView leagueHistoryText;

        public ViewHolder(final View itemView){
            super(itemView);
            leagueLogoImage = (ImageView) itemView.findViewById(R.id.league_logo);
            leagueHistoryText = (ListView) itemView.findViewById(R.id.league_history_text);

        }
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {


        holder.leagueLogoImage.setImageResource(R.drawable.serie_a_history);


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
