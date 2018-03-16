package com.example.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionActivity extends BaseActivity {

    TextView responeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responeText = (TextView) findViewById(R.id.respone_text);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        BufferedReader reader = null;
                        try {
                            URL url = new URL("http://www.baidu.com");
                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(8000);
                            connection.setReadTimeout(8000);
                            InputStream in = connection.getInputStream();
                            reader = new BufferedReader(new InputStreamReader(in));
                            StringBuilder respone = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                respone.append(line);
                            }
                            showRespone(respone.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (connection != null) {
                                connection.disconnect();
                            }
                        }
                    }
                }).start();
            }
        });
    }

    private void showRespone(final String respone) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responeText.setText(respone);
            }
        });
    }

    /**
     * 从网络获取json数据,(String byte[})
     *
     * @param path
     * @return
     */
    public static String getJsonByInternet(String path) {
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取其他页面的数据

    /**
     * POST请求获取数据
     */
    public static String postDownloadJson(String path, String post) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write(post);//post的参数 xx=xx&yy=yy
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
                bos.flush();
            }
            bos.close();
            return bos.toString("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
