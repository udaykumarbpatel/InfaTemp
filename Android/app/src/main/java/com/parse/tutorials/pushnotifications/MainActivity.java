package com.parse.tutorials.pushnotifications;

import android.app.Activity;
import android.os.Bundle;
import com.parse.ParseAnalytics;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpened(getIntent());

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
