package com.example.darren.finalyearproject;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import java.io.IOException;
import java.util.stream.Stream;

public class StreamActivity extends AppCompatActivity implements SurfaceHolder.Callback ,MediaPlayer.OnPreparedListener  {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    ProgressDialog loadDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(StreamActivity.this);
    }
//        final ProgressDialog loadDialog = ProgressDialog.show(StreamActivity.this, "Uno Momento Por Favor", "Stream Loading..", true);
//
//
//        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                mp.reset();
//                return false;
//            }
//        });
//
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                loadDialog.dismiss();
//                mp.start();
//
//            }
//        });
//
//        try {
//            mediaPlayer.setDataSource("http://192.168.43.62:8160");
//            mediaPlayer.prepareAsync();
//        }catch (IllegalArgumentException e) {
//        }catch (IllegalStateException e) {
//        }catch (IOException e){
//
//        }
//    }

        @Override
        public void surfaceCreated (SurfaceHolder holder){
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(surfaceHolder);
            try{
                mediaPlayer.setDataSource("http://192.168.43.62:8160");
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(StreamActivity.this);

            }catch (IOException e){
                e.printStackTrace();
            }

        }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
        public void surfaceChanged (SurfaceHolder holder,int format, int width, int height){

        }

        @Override
        public void surfaceDestroyed (SurfaceHolder holder){

        }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }
}


