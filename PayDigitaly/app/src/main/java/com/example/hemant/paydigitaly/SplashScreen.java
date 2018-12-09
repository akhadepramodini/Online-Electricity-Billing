package com.example.hemant.paydigitaly;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreen extends AppCompatActivity {
    ImageView i1;
    TextView tv;

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        i1 = findViewById(R.id.imageVIew);
        tv = findViewById(R.id.textView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1 = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i1);

            }
        }, SPLASH_TIME_OUT);
    }


}