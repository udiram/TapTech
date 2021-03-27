package com.example.firsttestapp.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;

public class LauncherActivity extends AppCompatActivity {
    private static final int SLEEP_TIME = 8000;//change into milliseconds, work on tapping to skip animation.
    VideoView videoView;
    MediaController mediaController;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        videoView = (VideoView) findViewById(R.id.videoView);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.slogo1;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        //videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });


        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(LauncherActivity.this, PasswordActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }

}
