package com.mikhail.sportsnewshistoryrecords.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mikhail on 4/27/16.
 */
public class ModelObjectAdaptor<T> extends RecyclerView.Adapter<ModelObjectAdaptor.ViewHolder> {

    private List<T> modelObject;
    Context context;

    public ModelObjectAdaptor(List<T> modelObject) {
        this.modelObject = modelObject;
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
    public ModelObjectAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_main_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T modelObject = this.modelObject.get(position);
        holder.headline.setText(modelObject.toString());
        holder.articleInfo.setText(modelObject.getAbstractResult());

        String imageURI = modelObject.get(position).getThumbnail_standard();
        if (imageURI.isEmpty()) {
            imageURI = "R.drawable.nyt_icon";
        }

        Picasso.with(context)
                .load(imageURI)
                .placeholder(R.drawable.nyt_icon)
                .resize(100, 100)
                .centerCrop()
                .into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return modelObject.size();
    }
}
