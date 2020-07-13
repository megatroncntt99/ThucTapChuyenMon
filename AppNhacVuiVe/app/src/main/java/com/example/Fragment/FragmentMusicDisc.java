package com.example.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMusicDisc extends Fragment {
    CircleImageView circleImageViewDiscSong;
    ObjectAnimator objectAnimator;
    View viewMusicDisc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMusicDisc=inflater.inflate(R.layout.fragment_music_disc,container,false);
        remap();
        return viewMusicDisc;
    }

    private void remap() {


        circleImageViewDiscSong=viewMusicDisc.findViewById(R.id.circleImageViewDiscSong);
        objectAnimator=ObjectAnimator.ofFloat(circleImageViewDiscSong,"rotation",0f, 360f);
        objectAnimator.setDuration(15000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setStartDelay(2000);
        objectAnimator.setInterpolator(new LinearInterpolator());
    }
    public void getImageMusicDisc(String img){
        Picasso.get().load(img).into(circleImageViewDiscSong);
    }
    public void getStartObjectAnimator(){

        objectAnimator.start();

    }

    public void getPauseObjectAnimator(){

        objectAnimator.pause();
    }

    public void getResumeObjectAnimator(){

        objectAnimator.resume();

    }

    public void getStopObjectAnimator(){
        objectAnimator.start();
        objectAnimator.pause();

    }

}
