package com.example.android;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 *
 *  活动体控制类
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
        //杀掉当前进程ID
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void finishAll(){
        for(Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
                //杀掉当前进程ID
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        activities.clear();
    }
}
