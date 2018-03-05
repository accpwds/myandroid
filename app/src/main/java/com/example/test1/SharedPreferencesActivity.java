package com.example.test1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *  SharedPreferences 保存信息与读取信息
 *
 */
public class SharedPreferencesActivity extends BaseActivity {

    private static final String TAG="SharedPreferencesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save_data = (Button) findViewById(R.id.save_data);
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age",28);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });
        final Button restore_data = (Button) findViewById(R.id.restore_data);
        final TextView restore_text = (TextView) findViewById(R.id.restore_text);
        restore_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                String name = preferences.getString("name","");
                int age = preferences.getInt("age",0);
                boolean married = preferences.getBoolean("married",false);
                Log.d(TAG, "name is" + name);
                Log.d(TAG,"age is " + age);
                Log.d(TAG,"married is " + married);
                restore_text.setText("name is" + name + " age is " + age + " married is " + married);
            }
        });
    }
}
