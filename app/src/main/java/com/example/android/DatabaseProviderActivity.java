package com.example.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Administrator on 2018/3/9.
 *
 *  实现跨程序共享数据
 */

public class DatabaseProviderActivity extends BaseActivity {

    private String newId;

    private static final String URI = "content://com.example.databasetest.provider/book";

    private static final String TAG = "DatabaseProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button select = (Button) findViewById(R.id.select1);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(URI);
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                StringBuilder sb = new StringBuilder();
                TextView queryText = (TextView) findViewById(R.id.query_text3);
                if (cursor != null){
                    while (cursor.moveToNext()){
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));

                        Log.d(TAG, "book id is " + id);
                        Log.d(TAG, "book name is " + name);
                        Log.d(TAG, "book author is " + author);
                        Log.d(TAG, "book pages is " + pages);
                        Log.d(TAG, "book price is " + price);

                        sb.append("book id is " + id + "\n");
                        sb.append("book name is " + name + "\n");
                        sb.append("book author is " + author + "\n");
                        sb.append("book pages is " + pages + "\n");
                        sb.append("book price is " + price + "\n\n");

                    }
                    cursor.close();
                    queryText.setText(sb.toString());
                }else {
                    Toast.makeText(DatabaseProviderActivity.this
                            ,"not data to show",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button insert = (Button) findViewById(R.id.insert1);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(URI);
                ContentValues values = new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("price",new Random().nextInt(50));
                values.put("pages",new Random().nextInt(500));
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
                Toast.makeText(DatabaseProviderActivity.this
                        ,"insert success ",Toast.LENGTH_SHORT).show();
            }
        });

        Button update = (Button) findViewById(R.id.update1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(URI + "/"+ newId);
                ContentValues values = new ContentValues();
                values.put("name","A Storm of Swords");
                values.put("pages",new Random().nextInt(500));
                values.put("price",new Random().nextInt(50));
                int resultRow = getContentResolver().update(uri,values,null,null);
                if (resultRow > 0){
                    Toast.makeText(DatabaseProviderActivity.this
                            ,"update success " + resultRow,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DatabaseProviderActivity.this
                            ,"not data to update",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button delete = (Button) findViewById(R.id.delete1);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(URI + "/" + newId);
                int resultRow = getContentResolver().delete(uri,null,null);
                if (resultRow > 0){
                    Toast.makeText(DatabaseProviderActivity.this
                            ,"delete success " + resultRow,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DatabaseProviderActivity.this
                            ,"not data to delete" + resultRow,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
