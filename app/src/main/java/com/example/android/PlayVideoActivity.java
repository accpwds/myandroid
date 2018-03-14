package com.example.android;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class PlayVideoActivity extends BaseActivity implements View.OnClickListener {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        videoView = (VideoView) findViewById(R.id.videoView);
        Button play = (Button) findViewById(R.id.playVideo);
        Button pause = (Button) findViewById(R.id.pauseVideo);
        Button stop = (Button) findViewById(R.id.replayVideo);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(PlayVideoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayVideoActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        } else {
            initVideoPath();
        }
    }

    private void initVideoPath(){
        try {
            File file = new File(Environment.getExternalStorageDirectory(),"video.mp4");
            Log.d("PlayVideoActivity",file.getPath().toString());
            videoView.setVideoPath(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                } else {
                    Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null){
            videoView.suspend();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playVideo:
                if (!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.pauseVideo:
                if (videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.replayVideo:
                if (videoView.isPlaying()){
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }
}
