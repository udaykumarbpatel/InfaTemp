package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;


public class Agenda extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        WebView wv = (WebView)findViewById(R.id.webView);
        wv.setBackgroundColor(Color.TRANSPARENT);

        wv.loadUrl("file:///android_asset/table.html");

    }
}
