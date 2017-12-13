package com.example.snow;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler x = new Handler();
        x.postDelayed(new splashhandler(), 2000);

    }
    class splashhandler implements Runnable{
        @Override
        public void run() {
            startActivity(new Intent(getApplication(),MainActivity.class));
            SplashScreenActivity.this.finish();
        }
    }
}
