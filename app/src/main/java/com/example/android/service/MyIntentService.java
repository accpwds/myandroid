package com.example.android.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/21.
 */

public class MyIntentService extends IntentService {

    public MyIntentService(){

        super("MyIntentService"); //调用父类的有参构造涵数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("MyIntentService","Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestory executend");
    }
}
