package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000; // 2 seconds
    private TextView textView5;
    private View view1, view2, view3;
    private Animation slideUpAnimation;
    private Animation slidedownAnimation;
    private Animation fadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Get references to your views
        textView5 = findViewById(R.id.textView5);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);

        // Load animations
        slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        slidedownAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Apply animations to views
        textView5.startAnimation(slidedownAnimation);
        view1.startAnimation(slideUpAnimation);
        view2.startAnimation(slideUpAnimation);
        view3.startAnimation(slideUpAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an intent to start the main activity
                Intent mainIntent = new Intent(Splash.this, Terms.class);
                startActivity(mainIntent);

                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
