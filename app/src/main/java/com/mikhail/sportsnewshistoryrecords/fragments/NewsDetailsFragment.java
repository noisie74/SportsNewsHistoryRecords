package com.mikhail.sportsnewshistoryrecords.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.database.ArticleSaveForLater;
import com.mikhail.sportsnewshistoryrecords.database.SaveSQLiteHelper;

/**
 * Created by Mikhail on 4/29/16.
 */
public class NewsDetailsFragment extends Fragment {

    private View v;
    private WebView historyWebView;
    String[] articleDetails;
    public MenuItem saveLater;
    public ProgressBar progress;
    private static final String TAG = "ArticleStory Fragment";
    public String htmlSaveForLater;
    public SQLiteDatabase db;
    Toolbar toolbar;
    Spinner spinner;
    ControlToolbar controlToolbar;
    String[] leaguesArticleDetails;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.histoy_web_view, container, false);
        historyWebView = (WebView) v.findViewById(R.id.article_web_view);

        Bundle article = getArguments();
        articleDetails = article.getStringArray("article");

        Bundle leaguesNewsDetails = getArguments();
        leaguesArticleDetails = leaguesNewsDetails.getStringArray("searchedArticle");


        controlToolbar.showSpinner(false);
        controlToolbar.showTitle(false);

        progress = (ProgressBar) v.findViewById(R.id.progress_bar);

        WebSettings webSettings = historyWebView.getSettings();
        historyWebView.setWebViewClient(new WebViewClientDemo()); //opens url in app, not in default browser
        webSettings.setJavaScriptEnabled(true); //turn js on for hacking and giving better ux
        historyWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        if (leaguesArticleDetails == null){
            historyWebView.loadUrl(articleDetails[2]);

        }
        if (articleDetails == null){
            historyWebView.loadUrl(leaguesArticleDetails[1]);
        }

        setHasOptionsMenu(true);

        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            controlToolbar = (ControlToolbar) getActivity();
        }catch (ClassCastException ex){
            throw new ClassCastException();
        }

    }


    public interface ControlToolbar {
        void showSpinner(boolean visible);

        void showTitle(boolean visible);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
        saveLater = (MenuItem) menu.findItem(R.id.save_later);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.share) {
            Log.i(TAG, "Share button clicked!");

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, articleDetails[2]);
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
            startActivity(Intent.createChooser(intent, "Share"));
            return true;
        } else if (id == R.id.save_later) {
            ArticleSaveForLater article = new ArticleSaveForLater(htmlSaveForLater, articleDetails[1], articleDetails[4], articleDetails[2], articleDetails[3]);
            insertIntoDbFromArticle(article);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private class WebViewClientDemo extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            historyWebView.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            progress.setVisibility(View.GONE);
            saveLater.setVisible(true);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progress.setVisibility(View.VISIBLE);
            saveLater.setVisible(false);
        }
    }

    public class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showHTML(String html) {
        }
    }

    private long insertIntoDbFromArticle(ArticleSaveForLater article) {
        long newRowId = 0l;

        Cursor cursor;
        cursor = SaveSQLiteHelper.getInstance(getContext()).getAllSavedArticles();
        int isUniqueArticle = SaveSQLiteHelper.checkURLforDuplicate(article.getUrl(), cursor);
        cursor.close();

        if (isUniqueArticle == 0) {
            ContentValues values = new ContentValues();
            values.put(SaveSQLiteHelper.COL_HTML, article.getHtml());
            values.put(SaveSQLiteHelper.COL_TITLE, article.getTitle());
            values.put(SaveSQLiteHelper.COL_URL, article.getUrl());
            values.put(SaveSQLiteHelper.COL_SNIPPET, article.getSnippet());
            values.put(SaveSQLiteHelper.COL_IMAGE, article.getImage());
            values.put(SaveSQLiteHelper.COL_CODE, article.getCode());
            newRowId = db.insert(SaveSQLiteHelper.ARTICLES_TABLE_NAME, null, values);
            Toast.makeText(getContext(), "You saved " + article.getTitle(), Toast.LENGTH_LONG).show();
        } else if (isUniqueArticle == -1) {
            Toast.makeText(getContext(), "Out of Space! Delete some old articles", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "You have already saved this article!", Toast.LENGTH_SHORT).show();
        }
        return newRowId;

    }

}
