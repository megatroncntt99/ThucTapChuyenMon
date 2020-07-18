package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView image;
    TextView logo,slogan;
    Animation topAnim,leftAnim,rightAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        remap();
        addEvent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                finish();
            }
        },5000);

    }

    private void addEvent() {
        image.setAnimation(topAnim);
        logo.setAnimation(rightAnim);
        slogan.setAnimation(leftAnim);
    }

    private void remap() {
        topAnim = AnimationUtils.loadAnimation(this, R.anim.anim_top);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.anim_left);
        rightAnim=AnimationUtils.loadAnimation(this,R.anim.anim_right);

        image = findViewById(R.id.imgSplashScreen);
        logo = findViewById(R.id.txtNameApp);
        slogan = findViewById(R.id.txtSplash);
    }
}
