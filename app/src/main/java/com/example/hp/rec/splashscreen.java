package com.example.hp.rec;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.TimerTask;

/**
 * Created by Govind on 30-03-2015.
 */
public class splashscreen extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // our layout xml
        setContentView(R.layout.img);

        // we're gonna use a timer task to show the main activity after 4 seconds
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                // go to the main activity
                Intent nextActivity = new Intent(splashscreen.this,instructions.class);
                startActivity(nextActivity);

                // make sure splash screen activity is gone
                splashscreen.this.finish();

            }

        };

    }
}
