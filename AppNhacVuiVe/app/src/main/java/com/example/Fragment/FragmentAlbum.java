package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.AlbumAdapter;
import com.example.Model.Album;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllAlbumActivity;
import com.example.appnhacvuive.R;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAlbum extends Fragment {
    public static final String TAG="AAA";
    View viewAlbum;
    RecyclerView recyclerViewAlbum;
    RelativeLayout rlAlbum;
    AlbumAdapter albumAdapter;
    TextView txtSeeMoreAlbumHot;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewAlbum=inflater.inflate(R.layout.fragment_album,null);
        remap();
        GetData();
        addEvent();
        return viewAlbum;
    }

    private void addEvent() {
        rlAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllAlbumActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });
        txtSeeMoreAlbumHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllAlbumActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });
    }


    private void remap() {
        rlAlbum=viewAlbum.findViewById(R.id.rlAlbum);

        recyclerViewAlbum=viewAlbum.findViewById(R.id.rwAlbum);
        recyclerViewAlbum.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewAlbum.setLayoutManager(layoutManager);
        txtSeeMoreAlbumHot=viewAlbum.findViewById(R.id.txtSeeMoreAlbumHot);
    }
    private void GetData() {
        DataService dataService= APIService.getService();
        Call<ArrayList<Album>> callBack=dataService.GetDataAlbum();

        callBack.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                ArrayList<Album> albumArrayList=response.body();
                Collections.shuffle(albumArrayList);
                albumAdapter=new AlbumAdapter(getActivity(),albumArrayList);
                recyclerViewAlbum.setAdapter(albumAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
    }
}
