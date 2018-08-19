package com.example.darren.finalyearproject;

import android.app.ProgressDialog;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.VideoView;

public class HomeActivity extends AppCompatActivity {

    ProgressDialog mDialog;
    VideoView videoView;

    String videoURL = "http://192.186.43.62:8160";
    MediaPlayer mediaPlayer = new MediaPlayer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        videoView = (VideoView)findViewById(R.id.videoView);
        final ProgressDialog progressDialog = ProgressDialog.show(HomeActivity.this, "Please wait...", "Loading Live Scene...", true);

        try {
            if (!videoView.isPlaying()) {
                Uri uri = Uri.parse(videoURL);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
            }
            else {
                videoView.pause();

            }
        }

        catch (Exception ex)
        {

        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                progressDialog.dismiss();
                videoView.start();
            }
        });

    }
}
