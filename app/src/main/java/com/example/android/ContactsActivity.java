package com.example.android;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 *
 * 使用Uri获取系统联系人
 */

public class ContactsActivity extends BaseActivity {

    ArrayAdapter<String> adapter;

    List<String> contactsList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
