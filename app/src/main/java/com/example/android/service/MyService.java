package com.example.android.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.android.R;

public class MyService extends Service {

    public DownloadBinder mBinder = new DownloadBinder();

    public class DownloadBinder extends Binder{

        public void startDownload(){
            Log.d("MyService","StartDownload excutend");
        }

        public int getProgress(){
            Log.d("MyService","getProgress excutend");
            return 0;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate executend");
        Intent intent = new Intent(this,MyService.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //多线程
        new Thread(new Runnable() {
            @Override
            public void run() {

                stopSelf(); //执行完自动停止
            }
        }).start();
        Log.d("MyService","onStartCommand executend");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy executend");
    }
}
