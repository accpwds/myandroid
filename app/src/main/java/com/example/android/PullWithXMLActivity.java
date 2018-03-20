package com.example.android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.object.App;
import com.example.android.object.Result;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.example.android.HttpUtil;


/**
 * http://www.mvnrepository.com/artifact/com.google.code.gson/gson/2.8.0
 *
 * http://blog.csdn.net/qq_29269233/article/details/53352668  JSON解析
 *
 */
public class PullWithXMLActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "PullWithXMLActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.send_request) {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://apis.juhe.cn/mobile/get?phone=13429667914&key=9719c91bd4ac2647c67c6cd067b5cb8e").build();
                    //http://www.w3school.com.cn/example/xmle/cd_catalog.xml
                    Response response = client.newCall(request).execute();
                    String responeData = response.body().string();
                    Log.d(TAG,responeData);
                    //parseXMLWithPull(responeData);
                    //parseXMLWithSAX(responeData);
                    //parseJSONWithJSONObject(responeData);
                    parseJSONWithGSON(responeData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    /**
     * PULL方式解析XML
     *
     * @param xmlData
     */
    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int evenType = xmlPullParser.getEventType();
            StringBuilder sb = new StringBuilder();
            TextView responeText = (TextView) findViewById(R.id.respone_text);
            String title = "";
            String artist = "";
            String country = "";
            String price = "";
            String year = "";
            while (evenType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (evenType) {
                    case XmlPullParser.START_TAG:
                        if ("title".equalsIgnoreCase(nodeName)) {
                            title = xmlPullParser.nextText();
                            sb.append("title is " + title + "\n");
                        } else if ("artist".equalsIgnoreCase(nodeName)) {
                            artist = xmlPullParser.nextText();
                            sb.append("artist is " + artist + "\n");
                        } else if ("country".equalsIgnoreCase(nodeName)) {
                            country = xmlPullParser.nextText();
                            sb.append("country is " + country + "\n");
                        } else if ("price".equalsIgnoreCase(nodeName)) {
                            price = xmlPullParser.nextText();
                            sb.append("price is " + price + "\n");
                        } else if ("year".equalsIgnoreCase(nodeName)) {
                            year = xmlPullParser.nextText();
                            sb.append("year is " + year + "\n\n");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("cd".equalsIgnoreCase(nodeName)) {
                            Log.d(TAG, "title is " + title);
                            Log.d(TAG, "artist is " + artist);
                            Log.d(TAG, "country is " + country);
                            Log.d(TAG, "price is " + price);
                            Log.d(TAG, "year is " + year);
                        }
                        break;
                    default:
                        break;
                }
                evenType = xmlPullParser.next();
            }
            responeText.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithSAX(String xmldata) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmldata)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithJSONObject(String jsonData) {

        String json = "[\n" +
                "    {\n" +
                "        \"resultcode\": 1,\n" +
                "        \"reason\": \"http://blog.csdn.net/qq_29269233/f1.jpg\",\n" +
                "        \"result\": \"金鱼1\",\n" +
                "        \"error_code\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"resultcode\": 2,\n" +
                "        \"reason\": \"http://blog.csdn.net/qq_29269233/f2.jpg\",\n" +
                "        \"result\": \"金鱼2\",\n" +
                "        \"error_code\": 12.5\n" +
                "    }\n" +
                "]";

        try {
            /*JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String resultcode = jsonObject.getString("resultcode");
                String reason = jsonObject.getString("reason");
                String result = jsonObject.getString("result");
                String error_code = jsonObject.getString("error_code");
                Log.d(TAG, "resultCode is " + resultcode);
                Log.d(TAG, "reason is " + reason);
                Log.d(TAG, "result is " + result);
                Log.d(TAG, "error_code is " + error_code);
            }*/

            JSONObject jsonObject = new JSONObject(jsonData);
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithGSON(String jsonData){

        String json = "{\n" +
                "\t\"resultcode\":2, \"reason\":\"金鱼\", \n" +
                "\t\"result\":12.3, \n" +
                "\t\"error_code\":\"http://blog.csdn.net/qq_29269233/L05_Server/images/f1.jpg\"\n" +
                "}\n";

        Gson gson = new Gson();
        App app = gson.fromJson(jsonData,App.class);
        Log.d(TAG, "resultCode is " + app.getResultcode());
        Log.d(TAG, "reason is " + app.getReason());
        Log.d(TAG, "province is " + app.getResult().getProvince());
        Log.d(TAG, "city is " + app.getResult().getCity());
        Log.d(TAG, "areacode is " + app.getResult().getAreacode());
        Log.d(TAG, "zip is " + app.getResult().getZip());
        Log.d(TAG, "company is " + app.getResult().getCompany());
        Log.d(TAG, "card is " + app.getResult().getCard());
        Log.d(TAG, "error_code is " + app.getError_code());

    }
}
