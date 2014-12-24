package com.example.ukumar.infapushtemp;

import android.app.Application;

import com.parse.Parse;
import com.parse.PushService;

/**
 * Created by ukumar on 12/18/2014.
 */
public class Initializer extends Application {

    public Initializer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "K8LKeCtpnFUyGC3dBhu4XKUqoldwBQvHdzkHIsJ5", "kPz6yU5Tgn0H4F1Zwr9LLq1xWV6rElolLMQKZqUF");
        PushService.setDefaultPushCallback(this, MainActivity.class);
        PushService.subscribe(this, "Everyone", MainActivity.class);
    }
}
