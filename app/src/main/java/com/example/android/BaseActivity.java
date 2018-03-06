package com.example.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2018/2/25.
 *
 *  知晓当前是在哪一个活动体
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG="BaseActivity";

    //定义强制下线广播
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"执行 onCreate");
        Log.d(TAG, "添加Activity " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"执行 onDestroy");
        ActivityCollector.removeActivity(this);
        Log.d(TAG,"清除所有Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"执行 onResume");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.test1.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"执行 onPause");
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    class ForceOfflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll(); //销毁所有活动
                    Intent intent = new Intent(context,LoginActivity.class);
                    context.startActivity(intent); //重新启动LoginActivity
                }
            });
            builder.show();
        }
    }
}
