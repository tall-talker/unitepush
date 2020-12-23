package com.unite.push;

import android.app.Application;

public class UnitePushApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UnitePushManager.initPushSdk(this);
    }
}
