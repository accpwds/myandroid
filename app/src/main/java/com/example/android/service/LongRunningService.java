package com.example.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2018/3/26.
 *
 *  后台长时运行服务定时任务
 *
 */

public class LongRunningService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anhour = 60 * 60 * 1000;
        long triggerAtime = SystemClock.elapsedRealtime() + anhour;
        Intent i = new Intent(this,LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtime,pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
