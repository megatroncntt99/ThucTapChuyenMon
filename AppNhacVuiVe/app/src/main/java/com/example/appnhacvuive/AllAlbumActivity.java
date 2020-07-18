package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Adapter.AllAlbumAdapter;
import com.example.Model.Album;
import com.example.Service.APIService;
import com.example.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumActivity extends AppCompatActivity {

    Toolbar toolBarAllAlbum;
    GridView gridViewAllAlbum;
    ArrayList<Album> albumArrayList;
    ProgressBar progressBar;

    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album);
        remap();
        init();
        GetData();
        addEvent();
    }

    private void addEvent() {
        gridViewAllAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AllAlbumActivity.this,PlaylistSongActivity.class);
                intent.putExtra("idAlbum",albumArrayList.get(i));
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService= APIService.getService();
        Call<ArrayList<Album>> callBack=dataService.GetAllDataAlbum();
        callBack.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {

                albumArrayList=response.body();
                allAlbumAdapter=new AllAlbumAdapter(AllAlbumActivity.this,albumArrayList);
                gridViewAllAlbum.setAdapter(allAlbumAdapter);

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
    }


    private void remap() {
        toolBarAllAlbum=findViewById(R.id.toolBarAllAlbum);
        gridViewAllAlbum=findViewById(R.id.gridViewAllAlbum);
        albumArrayList=new ArrayList<>();
        progressBar=findViewById(R.id.progressBarAllAlbum);

    }


    private void init() {

        setSupportActionBar(toolBarAllAlbum);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Album hot");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolBarAllAlbum.setTitleTextColor(Color.BLACK);
            toolBarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_search){

            Intent intent = new Intent(AllAlbumActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
