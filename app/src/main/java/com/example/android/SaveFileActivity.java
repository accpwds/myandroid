package com.example.test1;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *  文件存储数据
 */
public class SaveFileActivity extends BaseActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);
        edit = (EditText) findViewById(R.id.edit);

        //获取data文件数据
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(SaveFileActivity.this,"Restoring successed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SaveFileActivity","onDestroy 返回或者关闭Activity则调用");
        String inputText = edit.getText().toString();
        save(inputText);
    }

    //保存内容到文本文件中
    private void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter write = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            write = new BufferedWriter(new OutputStreamWriter(out));
            write.write(inputText);
            Toast.makeText(SaveFileActivity.this,"saved successed",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (write != null){
                    write.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //获取存储文件中内容
    private String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ( (line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if ( reader != null ){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
