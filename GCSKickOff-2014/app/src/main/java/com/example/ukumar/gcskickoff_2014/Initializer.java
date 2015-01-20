package com.example.ukumar.gcskickoff_2014;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Created by ukumar on 1/16/2015.
 */
public class Initializer extends Application {

    public Initializer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "7iWjeRDKzL16nFog6MKGIRzgV1N4q9ITljlRcqOe", "2U0goFILP862vzuRKmkPKyXSojeRfSzMl6mgCWYd");
        PushService.setDefaultPushCallback(this, MainActivity.class);
        PushService.subscribe(this, "", MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}