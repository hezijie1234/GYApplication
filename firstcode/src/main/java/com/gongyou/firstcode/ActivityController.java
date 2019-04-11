package com.gongyou.firstcode;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezijie on 2019/4/9.
 */

public class ActivityController {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity ac :
                activities) {
            if (!ac.isFinishing()){
                ac.finish();
            }
        }
        activities.clear();
    }

}
