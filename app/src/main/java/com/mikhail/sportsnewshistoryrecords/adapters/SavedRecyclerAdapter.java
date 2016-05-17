package com.mikhail.sportsnewshistoryrecords.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.database.ArticleSaveForLater;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mikhail on 4/17/16.
 */
public class SavedRecyclerAdapter extends RecyclerView.Adapter<SavedRecyclerAdapter.SavedRecyclerViewHolder> {

    ArrayList<ArticleSaveForLater> data;
    Context context;
    private static OnItemClickListener listener;

    public SavedRecyclerAdapter(ArrayList<ArticleSaveForLater> data) {
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class SavedRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView headline;
        ImageView imageIcon;
        TextView articleAbstract;
        ArticleSaveForLater articlesSaved;

        public SavedRecyclerViewHolder(final View itemView) {
            super(itemView);

            headline = (TextView) itemView.findViewById(R.id.saved_headline);
            imageIcon = (ImageView)itemView.findViewById(R.id.saved_cardView_image);
            articleAbstract = (TextView)itemView.findViewById(R.id.saved_article_info_cardview);
            articlesSaved = new ArticleSaveForLater();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });

        }

    }

    public void setData(ArrayList<ArticleSaveForLater> data) {
        this.data = data;
    }

    @Override
    public SavedRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.save_recyclerview_layout, parent, false);
        SavedRecyclerViewHolder vh = new SavedRecyclerViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(SavedRecyclerViewHolder holder, int position) {
        holder.headline.setText(data.get(position).getTitle());
        holder.articleAbstract.setText(data.get(position).getSnippet());


        String imageURI = data.get(position).getImage();
        if(imageURI.isEmpty()){
            imageURI = "R.drawable.placeholder_image";
        }

        Picasso.with(context)
                .load(imageURI)
                .placeholder(R.drawable.about_image)
                .resize(160, 160)
                .centerCrop()
                .into(holder.imageIcon);
        holder.articleAbstract.setText(data.get(position).getSnippet());

    }

    @Override
    public int getItemCount() {
        return data.size();

    }
}

