package com.example.darren.finalyearproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

//    ProgressDialog mDialog;
//    VideoView videoView;
//    ImageView btnPlayPause;
//
//    String videoURL = "https://192.168.43.62:8160";

//    private View main;
//    private ImageView imageView;

    Button myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ***** ScreenShot Code ******
//        main = findViewById(R.id.main);
//        Button btn = (Button) findViewById(R.id.ssb);
//        imageView = (ImageView) findViewById(R.id.imageView);
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick (View view){
//                Bitmap b = Screenshot.takeScreenshotOfRootView(imageView);
//                imageView.setImageBitmap(b);
//                main.setBackgroundColor(Color.parseColor("#008080"));
//            }
//        });

//        Button btn = (Button)findViewById(R.id.open_activity_button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
//            }
//        });


        Button btn2 = (Button) findViewById(R.id.open_activity_button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StreamActivity.class));
            }
        });

        Button btn = (Button) findViewById(R.id.screenshotBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayActivity.class));
            }
        });



        //         *** Stream Code Attempt ***

//        videoView = (VideoView) findViewById(R.id.videoView);
//        btnPlayPause = (ImageButton) findViewById(R.id.btn_play_pause);
//        btnPlayPause.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        mDialog = new ProgressDialog(MainActivity.this);
//        mDialog.setMessage("Please wait...");
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.show();
//
//            try {
//                if (!videoView.isPlaying()) {
//                    Uri uri = Uri.parse(videoURL);
//                    videoView.setVideoURI(uri);
//                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                            btnPlayPause.setImageResource(R.drawable.ic_play);
//                        }
//                    });
//                } else {
//                    videoView.pause();
//                    btnPlayPause.setImageResource(R.drawable.ic_play);
//                }
//            }
//            catch(Exception ex)
//            {
//
//            }
//            videoView.requestFocus();
//            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mDialog.dismiss();
//                    mp.setLooping(true);
//                    videoView.start();
//                    btnPlayPause.setImageResource(R.drawable.ic_pause);
//                }
//            });


        myBtn = (Button) findViewById(R.id.ssb);
        myBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final RelativeLayout layout = (RelativeLayout) findViewById(R.id.main);

                layout.post(new Runnable() {

                    @Override
                    public void run() {

                        Bitmap pic = takeScreenShot(layout);

                        try {
                            if (pic != null) {
                                saveToInternalStorage(pic);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


    }

    private Bitmap takeScreenShot(View s)

    {
        Bitmap screenShot = null;

        try {
            int width = s.getMeasuredWidth();
            int height = s.getMeasuredHeight();

            screenShot = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            Canvas c = new Canvas(screenShot);
            s.draw(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenShot;
    }

    //save to internal storage
//        private void saveScreenshot(Bitmap bm)
//        {
//            ByteArrayOutputStream bao = null;
//            File file = null;
//
//
//            try
//            {
//                //writing to output stream
//                bao = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.PNG,40,bao);
//
//                //writing to file to save in internal storage
//                file = new File();
//                file.createNewFile();
//
//                FileOutputStream fos=new FileOutputStream(file);
//                fos.write(bao.toByteArray());
//                fos.close();
//
//            }catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//        }

    @NonNull
    private String saveToInternalStorage(Bitmap bm) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("ImageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, "screenshot.png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);

            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

//    private void loadImageFromStorage(String mypath) {
//
//        try {
//            File f = new File(mypath, "screenshot.jpg");
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img = (ImageView) findViewById(R.id.screenshotView);
//            img.setImageBitmap(b);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}

