package com.example.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity implements View.OnClickListener {

    TextView responeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responeText = (TextView) findViewById(R.id.respone_text);
        Button sendRespone = (Button) findViewById(R.id.send_respone);
        sendRequest.setOnClickListener(this);
        sendRespone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.send_request){
            sendRequestWithOkHttp();
        }

        if (v.getId() == R.id.send_respone){
            sendResponeWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    Response response = client.newCall(request).execute();
                    String responeData =response.body().string();
                    showRespone(responeData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendResponeWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username","admin")
                            .add("password","123456").build();
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    String responeData =response.body().string();
                    showRespone(responeData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showRespone(final String respone) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responeText.setText(respone);
            }
        });
    }


}
