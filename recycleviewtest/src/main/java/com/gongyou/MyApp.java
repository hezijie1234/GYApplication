package com.gongyou;

import android.app.Application;
import android.content.Context;

/**
 * Created by hezijie on 2018/12/10.
 */

public class MyApp extends Application {
    private static   Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
