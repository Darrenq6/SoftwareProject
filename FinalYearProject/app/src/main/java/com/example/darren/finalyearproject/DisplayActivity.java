package com.example.darren.finalyearproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

    }

        private void loadImageFromStorage(String path){

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("ImageDir", Context.MODE_PRIVATE);
           // File mypath = new File(directory, "screenshot.png");

        try{
            File f = new File(directory, "screenshot.png");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.ScreenShotView);
            img.setImageBitmap(b);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
