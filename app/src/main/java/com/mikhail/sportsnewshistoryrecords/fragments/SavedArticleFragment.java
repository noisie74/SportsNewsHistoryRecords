package com.mikhail.sportsnewshistoryrecords.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.adapters.SavedRecyclerAdapter;
import com.mikhail.sportsnewshistoryrecords.database.ArticleSaveForLater;
import com.mikhail.sportsnewshistoryrecords.database.SaveSQLiteHelper;
import com.mikhail.sportsnewshistoryrecords.interfaces.SavedArticleControl;

import java.util.ArrayList;

/**
 * Created by Mikhail on 5/6/16.
 */
public class SavedArticleFragment extends Fragment {
    public final static int savedArticleLimit = 20;
    public ArrayList<ArticleSaveForLater> articleLists;

    private SavedRecyclerAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private TextView storiesCount;
    SavedArticleControl savedArticleControl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.saved_recycleview_fragment, container, false);

        setViews(v);
        articleLists = new ArrayList<>();
        recycleAdapter = new SavedRecyclerAdapter(articleLists);

        getSavedArticles();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recycleAdapter.setOnItemClickListener(new SavedRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                storiesCount.setVisibility(View.GONE);
                articleLists.get(position);
                Bundle article = new Bundle();
                String[] articleDetails = {articleLists.get(position).getTitle(),
                        articleLists.get(position).getUrl(),
                        articleLists.get(position).getImage(),
                        articleLists.get(position).getSnippet(),
                        String.valueOf(articleLists.get(position).getCode()),
                        String.valueOf(articleLists.get(position).getId())};
                article.putStringArray("article", articleDetails);

                savedArticleControl.setSavedArticleBundle(article);

                Log.d("Saved Article View", articleLists.get(position).getImage() + "");



            }
        });

        return v;
    }

    private void getSavedArticles() {
        Cursor cursor;
        cursor = SaveSQLiteHelper.getInstance(getContext()).getAllSavedArticles();
        dumpCursorToArray(cursor);
        cursor.close();

        if (recyclerView != null) {
            recyclerView.setAdapter(recycleAdapter);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try { savedArticleControl = (SavedArticleControl) getActivity();

        }catch (ClassCastException ex ){
            throw ex;
        }
    }

    public void setViews(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.saved_recycle_view);
        storiesCount = (TextView) v.findViewById(R.id.stories_count);
        storiesCount.setVisibility(View.VISIBLE);

    }

    public void dumpCursorToArray(Cursor cursor) {
        articleLists.clear();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            ArticleSaveForLater article = new ArticleSaveForLater();
            Log.d("db output", cursor.getString(0));
            article.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_ID))));
            article.setTitle((cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_TITLE))));
            article.setSnippet((cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_SNIPPET))));
            article.setUrl((cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_URL))));
            article.setImage((cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_IMAGE))));
            article.setCode(Long.parseLong(cursor.getString(cursor.getColumnIndex(SaveSQLiteHelper.COL_CODE))));


            cursor.moveToNext();
            articleLists.add(article);
            recycleAdapter.notifyDataSetChanged();
        }

        storiesCount.setText("You have " + +articleLists.size()
                + "/" + String.valueOf(savedArticleLimit) + " " + "articles");


    }
}

