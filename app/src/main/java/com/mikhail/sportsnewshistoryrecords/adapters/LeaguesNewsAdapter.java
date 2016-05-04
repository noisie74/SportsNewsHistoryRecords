package com.mikhail.sportsnewshistoryrecords.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;
import com.mikhail.sportsnewshistoryrecords.model.search.ArticleSearch;
import com.mikhail.sportsnewshistoryrecords.model.search.Doc;
import com.mikhail.sportsnewshistoryrecords.model.search.Multimedia;
import com.mikhail.sportsnewshistoryrecords.model.search.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 4/28/16.
 */
public class LeaguesNewsAdapter extends RecyclerView.Adapter<LeaguesNewsAdapter.ViewHolder> {

    Context context;
    public List<Doc> docs;

    public LeaguesNewsAdapter(List<Doc>docs) {
        this.docs = docs;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView headline;
        public TextView articleInfo;
        public ImageView articleImage;

        public ViewHolder(final View itemView) {
            super(itemView);
            headline = (TextView) itemView.findViewById(R.id.headline);
            articleInfo = (TextView) itemView.findViewById(R.id.article_info);
            articleImage = (ImageView) itemView.findViewById(R.id.cardView_image);
        }

    }

    @Override
    public LeaguesNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_main_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.headline.setText(docs.get(position).getHeadline().getMain());
        holder.articleInfo.setText(leaguesSearchResults.getResponse().getDocs()[position].getLead_paragraph());
        String imageURI = null;
        Multimedia[] multiMedia = leaguesSearchResults.getResponse().getDocs()[position].getMultimedia();
        Log.d("MainActivity",  ""+ multiMedia.length);
        if (multiMedia != null && multiMedia.length > 0) {

            imageURI = multiMedia[0].getUrl();
        }
        if (imageURI == null){
            imageURI = "R.drawable.nyt_icon";

        }
        Picasso.with(context)
                .load("http://nytimes.com/" + imageURI)
                .placeholder(R.drawable.nyt_icon)
                .resize(200, 200)
                .centerCrop()
                .into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return leaguesSearchResults.getResponse().getDocs().length;
    }
}
