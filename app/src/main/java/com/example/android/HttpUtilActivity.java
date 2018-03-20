package com.example.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.object.App;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/19.
 */

public class HttpUtilActivity extends BaseActivity {

    private static final String TAG = "HttpUtilActivity";

    private static final String URL="http://apis.juhe.cn/mobile/get";

    TextView textView;

    StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button sendRequestByHttp = (Button) findViewById(R.id.send_respone);

        textView = (TextView) findViewById(R.id.respone_text);

        sendRequestByHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.sendHttpRequest(URL, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String respone) {
                        //HttpRequest回调具体内容
                        try {
                            JSONObject jsonObject = new JSONObject(respone);
                            String resultcode = jsonObject.getString("resultcode");
                            String reason = jsonObject.getString("reason");
                            String result = jsonObject.getString("result");
                            String error_code = jsonObject.getString("error_code");
                            Log.d(TAG, "resultCode is " + resultcode);
                            Log.d(TAG, "reason is " + reason);
                            Log.d(TAG, "result is " + result);
                            JSONObject jsonCity = new JSONObject(result);
                            String province = jsonCity.optString("province");
                            String city = jsonCity.optString("city");
                            String areacode = jsonCity.optString("areacode");
                            String zip = jsonCity.optString("zip");
                            String company = jsonCity.optString("company");
                            String card = jsonCity.optString("card");
                            Log.d(TAG, "province is " + province);
                            Log.d(TAG, "city is " + city);
                            Log.d(TAG, "areacode is " + areacode);
                            Log.d(TAG, "zip is " + zip);
                            Log.d(TAG, "company is " + company);
                            Log.d(TAG, "card is " + card);
                            Log.d(TAG, "error_code is " + error_code);

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG,e.getMessage());
                    }
                });
            }
        });



        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestBody body = new FormBody.Builder()
                        .add("phone","13429667914")
                        .add("key","9719c91bd4ac2647c67c6cd067b5cb8e")
                        .build();

                HttpUtil.sendOkHttpRequest(URL,body,new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //得到服务器返回的具体内容
                        String responeData = response.body().string();
                        Log.d(TAG,responeData);
                        sb = new StringBuffer();
                        Gson gson = new Gson();
                        App app = gson.fromJson(responeData,App.class);
                        Log.d(TAG, "resultCode is " + app.getResultcode());
                        Log.d(TAG, "reason is " + app.getReason());
                        Log.d(TAG, "province is " + app.getResult().getProvince());
                        Log.d(TAG, "city is " + app.getResult().getCity());
                        Log.d(TAG, "areacode is " + app.getResult().getAreacode());
                        Log.d(TAG, "zip is " + app.getResult().getZip());
                        Log.d(TAG, "company is " + app.getResult().getCompany());
                        Log.d(TAG, "card is " + app.getResult().getCard());
                        Log.d(TAG, "error_code is " + app.getError_code());

                        sb.append(responeData + "\n\n" +"resultCode is " + app.getResultcode() + "\n"
                                + "reason is " + app.getReason());
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handle.sendMessage(message);
                    }
                });
            }
        });
    }

    public static final int UPDATE_TEXT = 1;

    private Handler handle = new Handler(){

        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText(sb.toString());
                    break;
                default:
                    break;
            }
        }
    };
}
