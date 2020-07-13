package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.Adapter.MVMusicAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.appnhacvuive.MVMusicActivity;
import com.example.Model.MVMusic;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.R;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMV extends Fragment {


    View viewMV;
    ListView lvMV;
    MVMusicAdapter mvMusicAdapter;
    ArrayList<MVMusic> mvMusicArrayList;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMV=inflater.inflate(R.layout.fragment_mv,container,false);
        remap();
        GetData();

        return viewMV;
    }



    private void remap() {
        lvMV=viewMV.findViewById(R.id.lvMV);
        mvMusicArrayList=new ArrayList<>();
        progressBar=viewMV.findViewById(R.id.progressBarMV);
    }
   private void GetData() {

        progressBar.setVisibility(View.VISIBLE);
        DataService dataService= APIService.getService();
        Call<ArrayList<MVMusic>> callBack=dataService.GetDataMVMusic();
        callBack.enqueue(new Callback<ArrayList<MVMusic>>() {
            @Override
            public void onResponse(Call<ArrayList<MVMusic>> call, Response<ArrayList<MVMusic>> response) {
                mvMusicArrayList.clear();
                mvMusicArrayList=response.body();
                Collections.shuffle(mvMusicArrayList);
                mvMusicAdapter=new MVMusicAdapter(getActivity(),mvMusicArrayList);
                lvMV.setAdapter(mvMusicAdapter);
                FunctionDesign functionDesign=new FunctionDesign();
                functionDesign.setListViewHeightBasedOnChildren(lvMV);
                addEvent();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ArrayList<MVMusic>> call, Throwable t) {

            }
        });
    }
    private void addEvent() {
        lvMV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), MVMusicActivity.class);
                intent.putExtra("KEY_MV",mvMusicArrayList.get(i).getKeyMV());
                startActivity(intent);
            }
        });
    }

}
