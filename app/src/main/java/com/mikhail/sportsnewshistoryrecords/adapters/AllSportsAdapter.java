package com.mikhail.sportsnewshistoryrecords.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsMultimedia;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mikhail on 4/27/16.
 */
public class AllSportsAdapter extends RecyclerView.Adapter<AllSportsAdapter.ViewHolder> {

    public int fragmentType;
    public NytSportsResults nytSportsResults;
    private static OnItemClickListener listener;
    Context context;

    public AllSportsAdapter(NytSportsResults nytSportsResults) {
        this.nytSportsResults = nytSportsResults;
    }

    public AllSportsAdapter() {
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void updateData(NytSportsResults results){
        this.nytSportsResults = results;
        notifyDataSetChanged();
    }

    public void setFragmentType(int fragmentType) {
        this.fragmentType = fragmentType;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView headline;
        public TextView articleInfo;
        public TextView ago;


        public ImageView articleImage;

        public ViewHolder(final View itemView) {
            super(itemView);
            headline = (TextView) itemView.findViewById(R.id.headline);
            articleInfo = (TextView) itemView.findViewById(R.id.article_info);
            articleImage = (ImageView) itemView.findViewById(R.id.cardView_image);
            ago = (TextView) itemView.findViewById(R.id.ago);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }

    }

    @Override
    public AllSportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_main_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        long timeStamp = System.currentTimeMillis();
        holder.headline.setText(nytSportsResults.getResults()[position].getTitle());
        holder.articleInfo.setText(nytSportsResults.getResults()[position].getAbstractResult());
        String agoText = "posted " + getBiggestUnitTimeElapsed(nytSportsResults.getResults()[position].getCreated_date(), timeStamp) + " ago";
        holder.ago.setText(agoText);

        NytSportsMultimedia[] nytSportsMultimedias = nytSportsResults.getResults()[position].getMultimedia();
        String imageURI;
        if (nytSportsMultimedias.length > 0) {
           imageURI  = nytSportsMultimedias[nytSportsMultimedias.length-1].getUrl();
            if (imageURI.isEmpty()) {
                imageURI = "R.drawable.nyt_icon";
            }

        }else {

            imageURI = "R.drawable.nyt_icon";

        }
        Picasso.with(context)
                .load(imageURI)
                .placeholder(R.drawable.nyt_icon)
                .resize(250, 180)
                .centerCrop()
                .into(holder.articleImage);
    }


    @Override
    public int getItemCount() {
        return nytSportsResults.getResults().length;
    }


    public static String getBiggestUnitTimeElapsed(long timeStamp, long nowStamp){
        String time = "";
        long different = nowStamp - timeStamp;
        String[] unit = {"year", "month", "day", "hour", "minute", "second"};
        int numberOfUnits = unit.length;
        long[] multiple = {31536000000l, 2592000000l, 86400000l, 3600000l, 60000l, 1000l}; //units in millis
        long[] coef = {0,0,0,0,0,0};
        for(int i = 0; i < numberOfUnits; i++){
            coef[i] = different / multiple[i];
            different = different % multiple[i];
        }

        for(int i = 0; i < numberOfUnits; i++){
            if(coef[i] > 0) {
                time = coef[i] + " " + unit[i];
                if(coef[i] > 1){
                    time = time + "s";
                    break;
                }
            }
        }
        return time;
    }

}

//nytSportsResults.getResults()[position].getThumbnail_standard();