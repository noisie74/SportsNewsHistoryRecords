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
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;
import com.squareup.picasso.Picasso;

/**
 * Created by Mikhail on 4/27/16.
 */
public class AllSportsAdapter extends RecyclerView.Adapter<AllSportsAdapter.ViewHolder> {

    public NytSportsResults nytSportsResults;
    private static OnItemClickListener listener;

    Context context;

    public AllSportsAdapter(NytSportsResults nytSportsResults) {
        this.nytSportsResults = nytSportsResults;
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
    public AllSportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_main_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.headline.setText(nytSportsResults.getResults()[position].getTitle());
        holder.articleInfo.setText(nytSportsResults.getResults()[position].getAbstractResult());

        NytSportsMultimedia[] nytSportsMultimedias = nytSportsResults.getResults()[position].getMultimedia();
        String imageURI;
        if (nytSportsMultimedias.length > 0) {
           imageURI  = nytSportsMultimedias[nytSportsMultimedias.length-1].getUrl();
            if (imageURI.isEmpty()) {
                imageURI = "R.drawable.nyt_icon";
            }

        }else{

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
}
