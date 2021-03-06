package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Adapter.SongLikeAdapter;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllSongLikeActivity extends AppCompatActivity {

    ListView lvAllSongLike;
    Toolbar toolbarSuggest;

    SongLikeAdapter songLikeAdapter;

    ArrayList<Song> songArrayList;
    ProgressBar progressBar;
    FloatingActionButton btnPlaySongLike;

    boolean isLoading = false, limitData = false;
    View viewLoadMoreItem;

    myHandler handler;


    int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_song_like);
        remap();
        init();
        GetData(page);
        addEvent();


    }


    private void addEvent() {
        lvAllSongLike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.songArrayList.clear();
                Intent intent = new Intent(AllSongLikeActivity.this, PlaySongActivity.class);
                intent.putExtra("idSong", songArrayList.get(i));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });

        lvAllSongLike.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getLastVisiblePosition() == totalItemCount - 1 && totalItemCount!=0 && !isLoading && !limitData ) {
                    isLoading = true;
                    ThreadData thread = new ThreadData();
                    thread.start();
                }
            }
        });
        btnPlaySongLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(songArrayList.size()>0){
                    MainActivity.songArrayList.clear();
                    Intent intent=new Intent(AllSongLikeActivity.this,PlaySongActivity.class);
                    intent.putExtra("arraySong",songArrayList);
                    intent.putExtra("nameList","Các bài hát được yêu thích nhất");

                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                else {
                    Toast.makeText(AllSongLikeActivity.this, "Chưa cập nhật bài hát ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            Intent intent = new Intent(AllSongLikeActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void remap() {
        btnPlaySongLike=findViewById(R.id.btnPlaySongLike);
        progressBar = findViewById(R.id.progressBarAllSongLike);
        toolbarSuggest = findViewById(R.id.toolbarSuggest);
        lvAllSongLike = findViewById(R.id.lvAllSongLike);
        songArrayList = new ArrayList<>();
        songLikeAdapter=new SongLikeAdapter(AllSongLikeActivity.this,songArrayList);
        lvAllSongLike.setAdapter(songLikeAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        viewLoadMoreItem = inflater.inflate(R.layout.load_item_song, null);
        handler = new myHandler();



    }

    private void init() {
        setSupportActionBar(toolbarSuggest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSuggest.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Gợi ý");
        toolbarSuggest.setTitleTextColor(Color.WHITE);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();
    }

    private void GetData(int page) {
        btnPlaySongLike.setEnabled(false);
        DataService dataService = APIService.getService();
        Call<ArrayList<Song>> callBack = dataService.GetAllDataSongLike(String.valueOf(page));
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {
               if(response!=null && response.body().size()>0){
                   lvAllSongLike.removeFooterView(viewLoadMoreItem);
                   ArrayList<Song>songs=response.body();
                   songLikeAdapter.addItemSongLike(songs);
                   progressBar.setVisibility(View.GONE);
                   btnPlaySongLike.setEnabled(true);
               }
               else {
                   limitData=true;
                   lvAllSongLike.removeFooterView(viewLoadMoreItem);
               }

            }
            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });


    }

    public class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    lvAllSongLike.addFooterView(viewLoadMoreItem);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;

            }
        }
    }

    public class ThreadData extends Thread {

        @Override
        public void run() {
            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = handler.obtainMessage(1);
            handler.sendMessage(message);
            super.run();
        }
    }


}

