package com.example.www.mobsharesdk;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/3/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
