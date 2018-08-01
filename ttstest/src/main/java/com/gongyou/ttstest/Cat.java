package com.gongyou.ttstest;

import android.util.Log;

/**
 * Created by hezijie on 2018/7/20.
 */

public class Cat implements Animal{
    private  int age;



    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void eat() {
        Log.e("111", "eat: fish"  );
    }
}
