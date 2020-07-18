package com.example.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Adapter.MVMusicAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.appnhacvuive.MVMusicActivity;
import com.example.Model.MVMusic;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMV extends Fragment {


    View viewMV;
    ProgressBar progressBar;
    ListView lvMV;
    MVMusicAdapter mvMusicAdapter;
    ArrayList<MVMusic> mvMusicArrayList;

    int page;
    boolean isLoading=false,limitData;
    MyHandler myHandler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMV=inflater.inflate(R.layout.fragment_mv,container,false);
        page=1;
        limitData=false;
        remap();
        GetData(page);
        addEvent();

        return viewMV;
    }


    private void remap() {
        lvMV=viewMV.findViewById(R.id.lvMV);
        mvMusicArrayList=new ArrayList<>();
        mvMusicAdapter=new MVMusicAdapter(getActivity(),mvMusicArrayList);
        lvMV.setAdapter(mvMusicAdapter);
        progressBar=viewMV.findViewById(R.id.progressBarMVMusic);
        myHandler=new MyHandler();
    }
    private void GetData(int page) {

        DataService dataService= APIService.getService();
        Call<ArrayList<MVMusic>> callBack=dataService.GetDataMVMusic(String.valueOf(page));
        callBack.enqueue(new Callback<ArrayList<MVMusic>>() {
            @Override
            public void onResponse(Call<ArrayList<MVMusic>> call, Response<ArrayList<MVMusic>> response) {
                if(response!=null && response.body().size()>0){
                    progressBar.setVisibility(View.GONE);
                    ArrayList<MVMusic> mvMusics=response.body();
                    Collections.shuffle(mvMusics);
                    mvMusicAdapter.addMVMusic(mvMusics);
                    FunctionDesign functionDesign=new FunctionDesign();
                    functionDesign.setListViewHeightBasedOnChildren(lvMV);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    limitData=true;
                }


            }

            @Override
            public void onFailure(Call<ArrayList<MVMusic>> call, Throwable t) {

            }
        });
    }
    private void addEvent() {
        lvMV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.load_song);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if(ConnectionReceiver.isConnected()){
                            if ( MainActivity.mediaPlaySong != null && MainActivity.mediaPlaySong.isPlaying()  ) {
                                MainActivity.mediaPlaySong.pause();
                            }
                            Intent intent=new Intent(getActivity(), MVMusicActivity.class);
                            intent.putExtra("KEY_MV",mvMusicArrayList.get(i).getKeyMV());
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                        }
                        else {
                            Toast t1=Toast.makeText(getActivity(),"Không có kết nối mạng",Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER,0,0);
                            t1.show();
                        }
                        handler.removeCallbacks(this);
                    }
                },2000);
            }
        });
        lvMV.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(absListView.getLastVisiblePosition()== i2-1 && i2!=0 && !isLoading && !limitData){
                    isLoading=true;
                    DataThread dataThread=new DataThread();
                    dataThread.start();
                }
            }
        });
    }
    public class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {

            switch (msg.what){
                case 0:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:

                    GetData(++page);
                    isLoading=false;
                    break;
            }
        }
    }
    public class DataThread extends Thread {
        @Override
        public void run() {

            myHandler.obtainMessage(0);
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();

        }
    }

}
