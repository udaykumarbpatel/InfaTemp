package com.parse.tutorials.pushnotifications;

import com.parse.Parse;
import com.parse.PushService;

public class Application extends android.app.Application {

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "K8LKeCtpnFUyGC3dBhu4XKUqoldwBQvHdzkHIsJ5", "kPz6yU5Tgn0H4F1Zwr9LLq1xWV6rElolLMQKZqUF");
        PushService.setDefaultPushCallback(this, MainActivity.class);
    }
}