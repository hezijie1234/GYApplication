package com.gongyou.flashlight;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by hezijie on 2018/11/13.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationContext();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
