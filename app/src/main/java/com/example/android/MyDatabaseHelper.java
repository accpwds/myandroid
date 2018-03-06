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

    public static final String CREATE_BOOK = "create table Book (id integer PRIMARY KEY " +
            "autoincrement,author text,price real,pages integer,name text)";

    public static final String CREATE_CATEGORY="create table Category (id integer PRIMARY " +
            "KEY autoincrement,cateory_name text,category_code integer)";

    private Context mContext;

    /**
     *
     * @param context 当前文本对象
     * @param name 数据库名称
     * @param factory
     * @param version 升级版本号，大于当前版本号则执行升级操作
     *
     *
     */
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Create successed",Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     *
     *升级数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Book");
        db.execSQL("DROP TABLE IF EXISTS Cateory");
        onCreate(db);
    }
}
