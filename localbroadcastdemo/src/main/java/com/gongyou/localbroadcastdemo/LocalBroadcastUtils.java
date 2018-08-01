package com.gongyou.localbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hezijie on 2018/6/19.
 *
 * 用于广播
 */

public class LocalBroadcastUtils {

    private Context mContext;
    private static LocalBroadcastUtils mInstance;

    private Map<String,BroadcastReceiver> mReceiverMap;
    private static LocalBroadcastManager mLocalManager;

    private LocalBroadcastUtils(Context context){
        mContext = context.getApplicationContext();
        mReceiverMap = new HashMap<>();
        mLocalManager = LocalBroadcastManager.getInstance(context);

    }

    public static LocalBroadcastUtils getInstance(Context context){
        if (mInstance == null){
            synchronized (LocalBroadcastUtils.class){
                if (mInstance == null){
                    mInstance = new LocalBroadcastUtils(context);
                }
            }
        }
        return mInstance;
    }


    public void register(String action,BroadcastReceiver receiver){
        try{
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(action);
            mLocalManager.registerReceiver(receiver,intentFilter);
            mReceiverMap.put(action, receiver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param action
     * @param s 发送字符串广播信息
     */
    public void sendBroadcast(String action,String s){
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("String",s);
        mLocalManager.sendBroadcast(intent);
    }

    /**
     * @param action
     * 用于发送更新信息广播（不包括数据，只发送信号）
     */
    public void sendBroadcast(String action){
        sendBroadcast(action,"");
    }


    /**
     * @param action
     * @param obj
     * 发送对象信息，但是将对象信息转换成json字符串
     */
//    public void sendBroadcast(String action,Object obj){
//        try {
//            Intent intent = new Intent();
//            intent.setAction(action);
//            //在此将fastjson换成了gson解析。
//            intent.putExtra("result", gson.toJson(obj));
//            mLocalManager.sendBroadcast(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @param action
     * @param obj
     * 发送广播时传递对象信息
     */
    public void sendBroadcast(String action, Serializable obj) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("result", obj);
        mLocalManager.sendBroadcast(intent);
    }

    /**
     * 销毁广播
     *
     * @param action
     */
    public void unregister(String action) {
        if (mReceiverMap != null) {
            BroadcastReceiver receiver = mReceiverMap.remove(action);
            if (receiver != null) {
                mLocalManager.unregisterReceiver(receiver);
            }
        }
    }

}
