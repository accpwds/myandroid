package com.example.android.listener;

/**
 * Created by Administrator on 2018/3/19.
 *
 * 回调机制
 *
 */

public interface HttpCallbackListener {

    void onFinish(String respone);

    void onError(Exception e);
}
