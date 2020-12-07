package com.am.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends AppCompatActivity {

    ImageView image;
    TextView title;
    SimpleArcLoader arcLoader;
    Animation animationtopin,animationbottomin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        animationtopin= AnimationUtils.loadAnimation(this,R.anim.top_in);
        animationbottomin=AnimationUtils.loadAnimation(this,R.anim.bottom_in);

        image=(ImageView)findViewById(R.id.splash_image);
        title=(TextView)findViewById(R.id.splash_title);
        arcLoader=(SimpleArcLoader)findViewById(R.id.splah_arcloader);

        image.setAnimation(animationtopin);
        title.setAnimation(animationbottomin);

        arcLoader.setVisibility(View.INVISIBLE);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //arcLoader.stop();
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },1500);
    }
}