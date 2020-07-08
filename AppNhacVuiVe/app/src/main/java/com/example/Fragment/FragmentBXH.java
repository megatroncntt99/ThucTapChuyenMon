package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Adapter.TopSongAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.RankSong;
import com.example.Model.Song;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBXH extends Fragment {
    View viewBXH;
    ListView lvTopSong,lvTopMV;
    ArrayList<RankSong> rankSongArrayList;
    TopSongAdapter topSongAdapter;
    AdView adView;
    ProgressBar progressBar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       viewBXH=inflater.inflate(R.layout.fragment_bxh,container,false);
        remap();
        GetData();
        AddAdMods();
        addEvent();

        return viewBXH;
    }

    private void addEvent() {
        lvTopSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), PlaylistSongActivity.class);
                intent.putExtra("idRankSong",rankSongArrayList.get(i));
                startActivity(intent);
            }
        });
    }

    private void AddAdMods() {
        adView=viewBXH.findViewById(R.id.adViewBXH);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    private void remap() {
        lvTopSong=viewBXH.findViewById(R.id.lvTopSong);
        lvTopMV=viewBXH.findViewById(R.id.lvTopMV);
        rankSongArrayList=new ArrayList<>();
        progressBar=viewBXH.findViewById(R.id.progressBarBXHSong);


    }
    private void GetData() {
        rankSongArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService= APIService.getService();
        Call<ArrayList<RankSong>> callBack=dataService.GetDataRankSong();
        callBack.enqueue(new Callback<ArrayList<RankSong>>() {
            @Override
            public void onResponse(Call<ArrayList<RankSong>> call, Response<ArrayList<RankSong>> response) {

                rankSongArrayList=response.body();
                topSongAdapter=new TopSongAdapter(getActivity(),rankSongArrayList);
                lvTopSong.setAdapter(topSongAdapter);
                FunctionDesign functionDesign=new FunctionDesign();
                functionDesign.setListViewHeightBasedOnChildren(lvTopSong);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<RankSong>> call, Throwable t) {

            }
        });
    }



}
