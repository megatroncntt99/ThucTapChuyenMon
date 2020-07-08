package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Adapter.AllPlaylistAdapter;
import com.example.Model.Playlist;
import com.example.Service.APIService;
import com.example.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlaylistActivity extends AppCompatActivity {

    Toolbar toolbarAllPlaylist;
    RecyclerView recyclerViewAllPlaylist;
    AllPlaylistAdapter allPlaylistAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_playlist);
        remap();
        init();
        GetData();
    }




    private void remap() {
        progressBar=findViewById(R.id.progressBarAllPlaylist);


        toolbarAllPlaylist=findViewById(R.id.toolbarAllPlaylist);
        recyclerViewAllPlaylist=findViewById(R.id.recyclerViewAllPlaylist);
        recyclerViewAllPlaylist.setLayoutManager(new GridLayoutManager(AllPlaylistActivity.this,2));
    }
    private void init() {
        setSupportActionBar(toolbarAllPlaylist);
        if(getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Play lists");
            toolbarAllPlaylist.setTitleTextColor(Color.BLACK);
            toolbarAllPlaylist.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
    private void GetData() {
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService= APIService.getService();
        Call<ArrayList<Playlist>> callBack=dataService.GetAllDataPlaylists();

        callBack.enqueue(new Callback<ArrayList<Playlist>>() {
            @Override
            public void onResponse(Call<ArrayList<Playlist>> call, Response<ArrayList<Playlist>> response) {


                ArrayList<Playlist> playlistArrayList=response.body();

                allPlaylistAdapter=new AllPlaylistAdapter(AllPlaylistActivity.this,playlistArrayList);
                recyclerViewAllPlaylist.setAdapter(allPlaylistAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Playlist>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_search){
            finish();
            Toast.makeText(this, "Click vào ô tìm kiếm", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
