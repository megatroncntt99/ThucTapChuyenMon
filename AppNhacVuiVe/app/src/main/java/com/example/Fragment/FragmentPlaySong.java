package com.example.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnhacvuive.R;


public class FragmentPlaySong extends Fragment {
    
    View viewPlaySong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewPlaySong= inflater.inflate(R.layout.fragment_play_song, container, false);
        remap();
        return viewPlaySong;
    }

    private void remap() {
    }
}
