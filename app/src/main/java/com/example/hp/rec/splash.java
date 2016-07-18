package com.example.hp.rec;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.img);

        // our timer task.
        final TimerTask task = new TimerTask() {

            @Override
            public void run() {

                // we have to run it on UI thread so we won't get view error
                splash.this.runOnUiThread(new Runnable() {
                    public void run() {

                        // get the splash image
                        ImageView splashImage = (ImageView) splash.this.findViewById(R.id.imageViewSplashLogo);

                        // make the splash image invisible
                        splashImage.setVisibility(View.GONE);

                        // specify animation
                        Animation animFadeOut = AnimationUtils.loadAnimation(splash.this,R.anim.splash_screen_fadeout);

                        Animation animzoom= AnimationUtils.loadAnimation(splash.this,R.anim.zoom_text);
                        ImageView splashImage1;
                        splashImage1 = (ImageView) splash.this.findViewById(R.id.imagetext);
                        splashImage1.setVisibility(View.GONE);

                        // apply the animattion
                        splashImage1.startAnimation(animzoom);
                        splashImage.startAnimation(animFadeOut);


                    }
                });

            }

        };

        // Schedule a task for single execution after a specified delay.
        // Show splash screen for 5 seconds
        new Timer().schedule(task, 5000);
        // go to the main activity





    }

}