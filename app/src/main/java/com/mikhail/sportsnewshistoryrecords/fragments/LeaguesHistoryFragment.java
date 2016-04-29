package com.mikhail.sportsnewshistoryrecords.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mikhail.sportsnewshistoryrecords.R;

/**
 * Created by Mikhail on 4/29/16.
 */
public class LeaguesHistoryFragment extends Fragment {

    private View v;
    private WebView historyWebView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.histoy_web_view, container, false);
        historyWebView = (WebView) v.findViewById(R.id.league_web_view);

        WebSettings webSettings = historyWebView.getSettings();
        historyWebView.setWebViewClient(new WebViewClientDemo()); //opens url in app, not in default browser
        webSettings.setJavaScriptEnabled(true); //turn js on for hacking and giving better ux
        historyWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        historyWebView.loadUrl("https://en.wikipedia.org/wiki/Serie_A");

        return v;
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

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }
    }

    public class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showHTML(String html) {
        }
    }

}
