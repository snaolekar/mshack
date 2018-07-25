package com.mshack.mshack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ResultWebView extends AppCompatActivity {

    private WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_web_view2);
        Intent intent = getIntent();
        String url = intent.getStringExtra(Search_Activity.URL_OPEN);
        showInWebView(url);
    }

    void showInWebView(String url){
        webView = findViewById(R.id.webview1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
