package com.example.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/5.
 */

public class SQLiteDatabaseActivity extends BaseActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);

        //创建数据库
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        //插入数据
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始插入第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.69);
                db.insert("Book",null,values);
                values.clear();
                //开始插入第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);
                Toast.makeText(SQLiteDatabaseActivity.this,"insert success",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //更新数据
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"});
                Toast.makeText(SQLiteDatabaseActivity.this,"update success",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //删除数据
        Button delData = (Button) findViewById(R.id.del_data);
        delData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                db.delete("Book","pages > ?",new String[]{"500"});
                Toast.makeText(SQLiteDatabaseActivity.this,"delete success",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //查询数据
        Button seleteData = (Button) findViewById(R.id.select_data);
        seleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                StringBuilder sb = new StringBuilder();
                TextView query_text = (TextView) findViewById(R.id.query_text);
                if (cursor.moveToFirst()){
                    do {
                        //遍历Cusor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Log.d("SQLiteDatabaseActivity","name is " + name);
                        Log.d("SQLiteDatabaseActivity","author is " + author);
                        Log.d("SQLiteDatabaseActivity","page is " + pages);
                        Log.d("SQLiteDatabaseActivity","price is " + price);
                        sb.append("name is " + name + "\nauthor is " + author + "\npages is " + pages +
                                "\nprice is " + price + "\n\n");
                    } while (cursor.moveToNext());
                }else {
                    Toast.makeText(SQLiteDatabaseActivity.this,"no data",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                query_text.setText(sb.toString());
            }
        });
    }
}
