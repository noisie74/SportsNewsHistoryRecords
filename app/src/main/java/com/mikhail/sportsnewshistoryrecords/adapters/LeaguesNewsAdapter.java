package com.mikhail.sportsnewshistoryrecords.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.model.search.Doc;
import com.mikhail.sportsnewshistoryrecords.model.search.Multimedia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mikhail on 4/28/16.
 */
public class LeaguesNewsAdapter extends RecyclerView.Adapter<LeaguesNewsAdapter.ViewHolder> {

    Context context;
    public ArrayList<Doc> leaguesSearchResults;
    public static OnItemClickListener listener;
    private int placeholderPosition;

    public LeaguesNewsAdapter(ArrayList<Doc> leaguesSearchResults) {
        this.leaguesSearchResults = leaguesSearchResults;
    }


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
    public LeaguesNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_main_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.headline.setText(leaguesSearchResults.get(position).getHeadline().getMain());
        holder.articleInfo.setText(leaguesSearchResults.get(position).getLead_paragraph());
        String imageURI = null;
        Multimedia[] multiMedia = leaguesSearchResults.get(position).getMultimedia();
        if (multiMedia != null && multiMedia.length > 0) {

            imageURI = multiMedia[0].getUrl();
        }
        if (imageURI == null){
            imageURI = "R.drawable.nyt_icon";

        }
        int[] images = new int[2];
        images[0] = R.drawable.about_image;
        images[1] = R.drawable.placeholder_image;


        Picasso.with(context)
                .load(imageURI)
                .placeholder(randPlaceholder(images))
                .resize(200, 180)
                .centerCrop()
                .into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return leaguesSearchResults.size();
    }


    /**
     random placeholder images
     * @param placeholderDrawables
     * @return
     */
    private int randPlaceholder(int[] placeholderDrawables){


        placeholderPosition++;

        return placeholderDrawables[placeholderPosition % placeholderDrawables.length];
    }
}
