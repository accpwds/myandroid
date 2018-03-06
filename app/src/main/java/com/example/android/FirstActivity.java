package com.example.android;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;

public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity";

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.d(TAG,"onCreate");

        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        //获取临时保存数据
        if (savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG,tempData);
        }else{
            Log.d(TAG,"is null");
        }

        Button button = (Button)findViewById(R.id.button);
        button.setText("Intent跳转与回调");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                String data = "Hello FirstActivity";
                Intent intent = new Intent(FirstActivity.this,MainActivity.class);
                intent.putExtra("extra_data",data);
                //startActivity(intent);
                startActivityForResult(intent,1);
            }
        });

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setText("监听网络变化广播");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);
                Intent intent = new Intent(FirstActivity.this,BroadCastActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText("ListView显示");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,FruitActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText("横向线性布局RecyclerView滚动显示");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,FruitActivity2.class);
                startActivity(intent);
            }
        });

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setText("瀑布流布局显示");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,FruitActivity3.class);
                startActivity(intent);
            }
        });

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setText("简单聊天器");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,MsgActivtiy.class);
                startActivity(intent);
            }
        });

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setText("发送自定义广播");
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.test1.MY_BROADCAST");
                //sendBroadcast(intent);
                sendOrderedBroadcast(intent,null); //发送有序广播
            }
        });

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setText("发送本地广播");
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,LocalBroadcastReceiver.class);
                startActivity(intent);
            }
        });

        Button button9 = (Button) findViewById(R.id.button9);
        button9.setText("强制下线");
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.test1.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

        Button button10 = (Button) findViewById(R.id.button10);
        button10.setText("文件存储数据");
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SaveFileActivity.class);
                startActivity(intent);
            }
        });

        Button button11 = (Button) findViewById(R.id.button11);
        button11.setText("SharedPreferences存储数据");
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });

        Button button12 = (Button) findViewById(R.id.button12);
        button12.setText("SQLiteDatabase操作数据库");
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SQLiteDatabaseActivity.class);
                startActivity(intent);
            }
        });

        Button button13 = (Button) findViewById(R.id.button13);
        button13.setText("LitePal创建数据库");
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
                Toast.makeText(FirstActivity.this,"create success",Toast.LENGTH_SHORT).show();
            }
        });
    }



    //接收Intent返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity",returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy 活动在被销毁之前调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause 在系统准备去启动或者恢复另一个活动的时候调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume 活动准备好和用户进行交互的时候调用，此时活动一定位于返回栈的栈顶，并且处理运行状态。");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart 活动由停止状态变为运行状态之前调用，也就是活动被重新启用了");
    }

    //临时数据保存
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key",tempData);
    }
}
