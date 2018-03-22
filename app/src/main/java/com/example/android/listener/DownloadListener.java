package com.example.android.listener;

/**
 * Created by Administrator on 2018/3/21.
 *
 * 下载监听器
 *
 */

public interface DownloadListener {

    void onProgress(int progress);  //通知当前的下载进度

    void onSuccess(); //通知下载成功事件

    void onFailed(); //通知下载失败事件

    void onPaused(); //通知暂停下载事件

    void onCanceled(); //通知取消下载事件
}
