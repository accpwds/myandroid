package com.example.test1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/5.
 *
 *  使用SQLiteOpen
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "CREATE TABLE Book (id INTEGER PRIMARY KEY " +
            "autocrement,author TEXT,price REAL,pages INTEGER,NAME TEXT)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Create successed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
