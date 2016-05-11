package com.mikhail.sportsnewshistoryrecords.fragments.details_fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.database.ArticleSaveForLater;
import com.mikhail.sportsnewshistoryrecords.database.SaveSQLiteHelper;
import com.mikhail.sportsnewshistoryrecords.interfaces.ControlLeaguesActivityLayout;


public class SportsLeaguesArticleDetailViewFragment extends Fragment {
    String[] articleDetails;
    View v;

    private static final String TAG = "ArticleStory Fragment";
    private ProgressBar progress;
    private WebView articleWebView;
    private String htmlSaveForLater;
    private SQLiteDatabase db;
    private MenuItem saveLater;
    ControlLeaguesActivityLayout controlLeaguesActivityLayout;

    /**
     * user interface to callback for fragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.histoy_web_view, container, false);
        articleWebView = (WebView) v.findViewById(R.id.article_web_view);


        Bundle article = getArguments();

        articleDetails = article.getStringArray("searchedArticle");

        controlLeaguesActivityLayout.showViewPager(false);
        controlLeaguesActivityLayout.showTabLayout(false);

        progress = (ProgressBar) v.findViewById(R.id.progress_bar);

        WebSettings webSettings = articleWebView.getSettings();
        articleWebView.setWebViewClient(new WebViewClientDemo()); //opens url in app, not in default browser
        webSettings.setJavaScriptEnabled(true); //turn js on for hacking and giving better ux
        articleWebView.addJavascriptInterface(new htmlJavaScriptInterface(), "HTMLOUT");

        articleWebView.loadUrl(articleDetails[1]);
        SaveSQLiteHelper mDbHelper = SaveSQLiteHelper.getInstance(getContext());
        db = mDbHelper.getWritableDatabase();

        Log.i(TAG, articleDetails[1]);

        setHasOptionsMenu(true);

        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            controlLeaguesActivityLayout = (ControlLeaguesActivityLayout) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException();
        }

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
            intent.putExtra(Intent.EXTRA_TEXT, articleDetails[1]);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this site!");
            startActivity(Intent.createChooser(intent, "Share"));
            return true;
        } else if (id == R.id.save_later) {
            ArticleSaveForLater article = new ArticleSaveForLater(htmlSaveForLater, articleDetails[0], articleDetails[3], articleDetails[1], articleDetails[2]);
            insertIntoDbFromSearchArticle(article);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    private class WebViewClientDemo extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progress.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            articleWebView.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            progress.setVisibility(View.GONE);
            if (articleWebView.isShown()){
                saveLater.setVisible(true);

            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progress.setVisibility(View.VISIBLE);
            saveLater.setVisible(false);
        }
    }

    public class htmlJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showHTML(String html) {
            htmlSaveForLater = html;
        }
    }


    private long insertIntoDbFromSearchArticle(ArticleSaveForLater article) {
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
