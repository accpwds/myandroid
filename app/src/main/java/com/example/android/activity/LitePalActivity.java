package com.example.android.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.object.Book;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/3/7.
 * <p>
 * 使用LitePal实现增删改查
 */

public class LitePalActivity extends BaseActivity {

    private static final String TAG = "LitePalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //插入数据
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                double random = new Random().nextInt(50);
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(new Random().nextInt(500));
                book.setPrice(random);
                book.setPress("unknow");
                book.save();
                Toast.makeText(LitePalActivity.this, "insert success", Toast.LENGTH_SHORT).show();
            }
        });

        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一种查询方式
                List<Book> books = DataSupport.findAll(Book.class);
                StringBuilder sb = new StringBuilder();
                TextView queryResult = (TextView) findViewById(R.id.query_text2);
                if (books.size() == 0) {
                    queryResult.setGravity(Gravity.CENTER);
                    queryResult.setText("not data to show");
                    queryResult.setPadding(5, 5, 5, 5);
                } else {
                    sb.append("共有" + books.size() + "条数据\n");
                    for (Book book : books) {
                        Log.d(TAG, "book id is " + book.getId());
                        Log.d(TAG, "book name is " + book.getName());
                        Log.d(TAG, "book author is " + book.getAuthor());
                        Log.d(TAG, "book pages is " + book.getPages());
                        Log.d(TAG, "book price is " + book.getPrice());
                        Log.d(TAG, "book press is " + book.getPress());
                        sb.append("book id is " + book.getId() + "\n");
                        sb.append("book name is " + book.getName() + "\n");
                        sb.append("book author is " + book.getAuthor() + "\n");
                        sb.append("book pages is " + book.getPages() + "\n");
                        sb.append("book price is " + book.getPrice() + "\n");
                        sb.append("book press is " + book.getPress() + "\n\n");
                    }
                    queryResult.setText(sb.toString());
                }

                //第二种查询方式
                List<Book> bookList = DataSupport.select("name", "author", "pages").
                        where("pages > ?", "400").order("pages").limit(10).offset(10).find(Book.class);
            }
        });

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                double random = new Random().nextInt(50);
                book.setPrice(random);
                book.setPress("Anchor");
                int result = book.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
                if (result > 0) {
                    Toast.makeText(LitePalActivity.this, "update success " + result, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LitePalActivity.this, "not data to update" + result, Toast.LENGTH_SHORT).show();
                }

            }
        });

        //删除数据
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(500);
                int result = DataSupport.deleteAll(Book.class, "pages > ?", Integer.toString(random));
                if (result > 0) {
                    Toast.makeText(LitePalActivity.this, "delete success " + result, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LitePalActivity.this, "not data to delete " + random, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
