package com.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.Model.Song;
import com.example.appnhacvuive.PlaySongActivity;

import com.example.appnhacvuive.R;

public class FragmentLyric extends Fragment {
    View viewLyric;
    TextView txtLyricSong,txtNameSong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLyric=inflater.inflate(R.layout.fragment_lyric,container,false);
        remap();
        GetData();
        return viewLyric;
    }



    private void remap() {
        txtLyricSong=viewLyric.findViewById(R.id.txtLyricSong);
        txtNameSong=viewLyric.findViewById(R.id.txtNameSongLyric);
    }
    private void GetData() {
        if(PlaySongActivity.songArrayList.size()>0){
            Song song=PlaySongActivity.song;
            txtNameSong.setText(song.getNameSong());
        }

    }
}
