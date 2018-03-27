package com.example.android.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.android.listener.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/19.
 *
 *
 *
 */

public class HttpUtil {

    /**
     * sendHttpRequestByGet
     *
     * @param address
     * @param listener
     */
    public static void sendHttpRequest(final String address,
                                       final HttpCallbackListener listener) {
        //判断网络是否可用，不可用直接Return掉
        if (!isNetworkAvailable()){
            Toast.makeText(MyApplication.getContext(),
                    "Network is Available",Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder respone = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        respone.append(line);
                    }
                    if (listener != null){
                        //回调onfinish方法
                        listener.onFinish(respone.toString());
                    }
                } catch (Exception e){
                    if (listener != null){
                        //回调onerror方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private static boolean isNetworkAvailable(){


        return true;
    }

    public static void sendHttpRequestDoPost(final String address, final String param,final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setUseCaches(false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                    // 设置接收类型否则返回415错误
                    //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
                    connection.setRequestProperty("accept","application/json");
                    // 往服务器里面发送数据
                    if (param != null && !TextUtils.isEmpty(param)) {
                        byte[] writebytes = param.getBytes();
                        // 设置文件长度
                        connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                        OutputStream outwritestream = connection.getOutputStream();
                        outwritestream.write(param.getBytes());
                        outwritestream.flush();
                        outwritestream.close();
                        //Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode());
                    }
                    /*if (connection.getResponseCode() == 200) {
                        reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        result = reader.readLine();
                    }*/
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder respone = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        respone.append(line);
                    }
                    if (listener != null){
                        listener.onFinish(respone.toString());
                    }
                } catch (Exception e){
                    if (listener != null){
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     *
     * OkHttp请求网络
     *
     * @param address
     * @param body
     * @param callback
     */
    public static void sendOkHttpRequest(String address, RequestBody body, okhttp3.Callback callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .post(body)
                .url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
