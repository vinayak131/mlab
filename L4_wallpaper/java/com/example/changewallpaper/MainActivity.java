package com.example.changewallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int[] images;
    Timer timer = new Timer();
    TimerTask timertask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View wallview = findViewById(R.id.wallView2);
        Button wpButton = findViewById(R.id.button);

        images = new  int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7};


        wpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        View wallview = findViewById(R.id.wallView);
                        int imglength= images.length;
                        Random random = new Random();
                        int rNum = random.nextInt(imglength);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                wallview.setBackground(ContextCompat.getDrawable(getApplicationContext(),images[rNum]));
                            }
                        });



                    }
                },0,3000);


            }
        });



    }
}