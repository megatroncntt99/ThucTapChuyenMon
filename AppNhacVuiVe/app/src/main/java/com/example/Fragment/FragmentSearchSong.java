package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Adapter.SongLikeAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Search;
import com.example.Model.Song;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.R;
import com.example.appnhacvuive.SearchActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSearchSong extends Fragment {

    View viewSearchSong;

    ListView lvSearchSong;
    ProgressBar progressBar;
    TextView txtNoDataSearch;

    ArrayList<Song> songArrayList;
    SongLikeAdapter songLikeAdapter;
    String key="*";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewSearchSong=inflater.inflate(R.layout.fragment_search_song, container, false);
        remap();
        return viewSearchSong;
    }



    public void GetData() {
        if( progressBar!=null){
            progressBar=viewSearchSong.findViewById(R.id.progressBarSearchSong);
        }
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService= APIService.getService();
        Call<Search> callback=dataService.GetDataSearchSong(key);
        callback.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Search search=response.body();
                songArrayList= (ArrayList<Song>) search.getSong();
                if(songArrayList.size()>0){
                    txtNoDataSearch.setVisibility(View.GONE);

                    songLikeAdapter=new SongLikeAdapter(getActivity(),songArrayList);
                    lvSearchSong.setAdapter(songLikeAdapter);
                    FunctionDesign functionDesign=new FunctionDesign();
                    functionDesign.setListViewHeightBasedOnChildren(lvSearchSong);
                    addEvent();
                }
                else {
                    songLikeAdapter=new SongLikeAdapter(getActivity(),songArrayList);
                    lvSearchSong.setAdapter(songLikeAdapter);
                    txtNoDataSearch.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    private void addEvent() {
        lvSearchSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), PlaySongActivity.class);
                intent.putExtra("idSong",songArrayList.get(i));
                startActivity(intent);
                SearchActivity.InsertHistorySearch();
            }
        });
    }


    public void GetDataSearchSong(String keySearch) {
       if(ConnectionReceiver.isConnected()){
           key=keySearch;
           GetData();
       }else {
           progressBar.setVisibility(View.VISIBLE);
           txtNoDataSearch.setVisibility(View.GONE);
       }


    }

    private void remap() {
        progressBar=viewSearchSong.findViewById(R.id.progressBarSearchSong);
        lvSearchSong=viewSearchSong.findViewById(R.id.lvSearchSong);
        txtNoDataSearch=viewSearchSong.findViewById(R.id.txtNoDataSearchSong);
        songArrayList=new ArrayList<>();
    }
}
