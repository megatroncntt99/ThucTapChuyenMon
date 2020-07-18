package com.example.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.PlaylistAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Playlist;
import com.example.Service.APIRetrofitClient;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllPlaylistActivity;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlaylist extends Fragment {
    View viewPlaylist;
    RecyclerView rwPlaylist;
    PlaylistAdapter playlistAdapter;
    TextView txtSeeMorePlaylist,txtSeeMorePlaylist1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPlaylist=inflater.inflate(R.layout.fragment_playlist,container,false);
        remap();
        GetData();
        addEvent();

        return viewPlaylist;
    }

    private void addEvent() {
        txtSeeMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllPlaylistActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
        txtSeeMorePlaylist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllPlaylistActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
    }

    private void remap() {
        txtSeeMorePlaylist=viewPlaylist.findViewById(R.id.txtSeeMorePlaylist);
        txtSeeMorePlaylist1=viewPlaylist.findViewById(R.id.txtSeeMorePlaylist1);

        rwPlaylist=viewPlaylist.findViewById(R.id.rwPlaylist);
        rwPlaylist.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        rwPlaylist.setLayoutManager(layoutManager);



    }
    private void GetData() {

        DataService dataService= APIService.getService();
        Call<ArrayList<Playlist>> callBack=dataService.GetDataPlaylists();
        callBack.enqueue(new Callback<ArrayList<Playlist>>() {
            @Override
            public void onResponse(Call<ArrayList<Playlist>> call, Response<ArrayList<Playlist>> response) {
                final ArrayList<Playlist> playlistArrayList=response.body();
                Collections.shuffle(playlistArrayList);

                playlistAdapter =new PlaylistAdapter(getActivity(),playlistArrayList);
                rwPlaylist.setAdapter(playlistAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Playlist>> call, Throwable t) {

            }
        });
    }



}
