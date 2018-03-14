package com.example.android;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;

import java.io.File;

public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.d(TAG, "onCreate");

        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //获取临时保存数据
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        } else {
            Log.d(TAG, "is null");
        }

        Button button = (Button) findViewById(R.id.button);
        button.setText("Intent跳转与回调");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                String data = "Hello FirstActivity";
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                intent.putExtra("extra_data", data);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
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
                Intent intent = new Intent(FirstActivity.this, BroadCastActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setText("ListView显示");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FruitActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText("横向线性布局RecyclerView滚动显示");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FruitActivity2.class);
                startActivity(intent);
            }
        });

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setText("瀑布流布局显示");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FruitActivity3.class);
                startActivity(intent);
            }
        });

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setText("简单聊天器");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, MsgActivtiy.class);
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
                sendOrderedBroadcast(intent, null); //发送有序广播
            }
        });

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setText("发送本地广播");
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LocalBroadcastReceiver.class);
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
                Intent intent = new Intent(FirstActivity.this, SaveFileActivity.class);
                startActivity(intent);
            }
        });

        Button button11 = (Button) findViewById(R.id.button11);
        button11.setText("SharedPreferences存储数据");
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });

        Button button12 = (Button) findViewById(R.id.button12);
        button12.setText("SQLiteDatabase操作数据库");
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SQLiteDatabaseActivity.class);
                startActivity(intent);
            }
        });

        Button button13 = (Button) findViewById(R.id.button13);
        button13.setText("LitePal创建数据库");
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LitePal创建数据库
                LitePal.getDatabase();
                Toast.makeText(FirstActivity.this, "create success", Toast.LENGTH_SHORT).show();
            }
        });

        Button button14 = (Button) findViewById(R.id.button14);
        button14.setText("LitePal_CRUD");
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LitePalActivity.class);
                startActivity(intent);
            }
        });

        Button button15 = (Button) findViewById(R.id.button15);
        button15.setText("运行时申请权限");
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        Button button16 = (Button) findViewById(R.id.button16);
        button16.setText("使用Uri获取联系人信息");
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        Button button17 = (Button) findViewById(R.id.button17);
        button17.setText("使用Uri_CRUD");
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DatabaseProviderActivity.class);
                startActivity(intent);
            }
        });

        Button button18 = (Button) findViewById(R.id.button18);
        button18.setText("发送通知");
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, MsgActivtiy.class);
                PendingIntent pi = PendingIntent.getActivity(FirstActivity.this, 0, intent, 0);
                NotificationManager manager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(FirstActivity.this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        //.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/MidEvilJaunt.ogg"))) //播放铃声
                        //.setVibrate(new long[]{0,1000,1000,1000})  //手机振动频率
                        //.setLights(Color.GREEN,1000,1000)   //指示灯显示
                        .setDefaults(NotificationCompat.DEFAULT_ALL)  //按照系统默认设置
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Lean how to build " +
                                "notifications,send and sync data,and use voice actions. Get the official" +
                                "Android IDE and developer tools build apps for Android."))   //长文显示
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                BitmapFactory.decodeResource(getResources(),R.drawable.big_image2)))  //大图片显示
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pi)  //点击通知栏进入Activity
                        .setAutoCancel(true)   //通知栏图标消失
                        .build();
                manager.notify(1, notification);
                //manager.cancel(1);   //通知栏图标消失
            }
        });

        Button button19 = (Button) findViewById(R.id.button19);
        button19.setText("调用摄像头拍照");
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, PictureActivity.class);
                startActivity(intent);
            }
        });

        Button button20 = (Button) findViewById(R.id.button20);
        button20.setText("播放音乐");
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, PlayMusicActivity.class);
                startActivity(intent);
            }
        });

        Button button21 = (Button) findViewById(R.id.button21);
        button21.setText("播放视频");
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, PlayVideoActivity.class);
                startActivity(intent);
            }
        });
    }

    //接收Intent返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy 活动在被销毁之前调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause 在系统准备去启动或者恢复另一个活动的时候调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume 活动准备好和用户进行交互的时候调用，此时活动一定位于返回栈的栈顶，并且处理运行状态。");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart 活动由停止状态变为运行状态之前调用，也就是活动被重新启用了");
    }

    //临时数据保存
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }
}
