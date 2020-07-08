package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.appnhacvuive.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MVMusicActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY="AIzaSyAGwnumwuYtG2WJkMbBMAvJiBlb8GZAEfA";
    YouTubePlayerView youTubePlayerView;
    ImageView imgBackMV;
    AdView adViewAdMobMV1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_v_music);
        remap();
        addEvent();
    }



    private void remap() {
        youTubePlayerView =findViewById(R.id.myYouTubeMV);
        youTubePlayerView.initialize(API_KEY,this);

        imgBackMV=findViewById(R.id.imgBack_MV);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        adViewAdMobMV1=findViewById(R.id.adView1);
        AdRequest adRequest1=new AdRequest.Builder().build();
        adViewAdMobMV1.loadAd(adRequest1);



    }
    private void addEvent() {
        imgBackMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Intent intent=getIntent();
        youTubePlayer.loadVideo(intent.getStringExtra("KEY_MV"));
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            youTubePlayerView.initialize(API_KEY,this);
        }
    }
}
