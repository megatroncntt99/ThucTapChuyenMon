package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Adapter.SongLikeAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllSongLikeActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSongLike extends Fragment {
    View viewSongLike;
    TextView txtGoiY;
    TextView btnSeeMoreSongLike;
    ListView lvSongLike;
    ArrayList<Song> songArrayList;
    SongLikeAdapter songLikeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewSongLike=inflater.inflate(R.layout.fragment_song_like,container,false);
        remap();

        addEvent();
        return viewSongLike;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetData();
    }

    private void addEvent() {
        lvSongLike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.songArrayList.clear();
                Intent intent=new Intent(getActivity(), PlaySongActivity.class);
                intent.putExtra("idSong",songArrayList.get(i));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);

            }
        });
        txtGoiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllSongLikeActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
        btnSeeMoreSongLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllSongLikeActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
    }

    private void remap() {
        txtGoiY=viewSongLike.findViewById(R.id.txtGoiY);
        btnSeeMoreSongLike=viewSongLike.findViewById(R.id.btnSeeMoreSongLike);
        lvSongLike=viewSongLike.findViewById(R.id.lvSongLike);

        songArrayList=new ArrayList<>();


    }
    private void GetData() {

        DataService dataService= APIService.getService();
        Call<ArrayList<Song>> callBack=dataService.GetDataSongLike();
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {
                songArrayList=response.body();
                songLikeAdapter=new SongLikeAdapter(getActivity(),songArrayList);
                lvSongLike.setAdapter(songLikeAdapter);
                new FunctionDesign().setListViewHeightBasedOnChildren(lvSongLike);

            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });
    }
}
