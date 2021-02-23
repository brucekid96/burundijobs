package com.burundijobs;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private ProgressBar progressBar;
    private boolean isWebViewLoadingFirstPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView)findViewById(R.id.wb);
        progressBar= findViewById(R.id.progresBar);



        myWebView.setWebViewClient(new WebViewClient() {
            @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(ProgressBar.VISIBLE);
                myWebView.setVisibility(View.INVISIBLE);
            }

            @Override public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(ProgressBar.GONE);
                myWebView.setVisibility(View.VISIBLE);
                isWebViewLoadingFirstPage=false;
            }
        });

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
            }
        });


        myWebView.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        myWebView.getSettings().setAllowFileAccess( true );
        myWebView.getSettings().setAppCacheEnabled( true );
        myWebView.getSettings().setJavaScriptEnabled( true );
        myWebView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );
        myWebView.loadUrl("https://www.burundijobs.bi");
    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


}
