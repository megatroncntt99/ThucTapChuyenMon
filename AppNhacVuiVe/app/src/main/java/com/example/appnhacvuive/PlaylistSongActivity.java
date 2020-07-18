package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.PlaylistSongAdapter;
import com.example.Fragment.FragmentPlaySong;
import com.example.Model.Advertisement;
import com.example.Model.Album;
import com.example.Model.Category;
import com.example.Model.Playlist;
import com.example.Model.RankSong;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistSongActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;

    Advertisement advertisement;
    Playlist playlist;
    Category category;
    Album album;
    RankSong rankSong;
    Toolbar toolbar;
    FloatingActionButton btnPlaylistRandom;
    RecyclerView recyclerViewPlayList;
    ImageView imgPlaylistSong;

    TextView txtSoLuongBaiHat;
    ImageView UnLikePlaylistSong, LikePlaylistSong;
    ProgressBar progressBar;

    PlaylistSongAdapter playlistSongAdapter;
    ArrayList<Song> songArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_song);

        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        remap();
        getDataIntent();

        init();
        if (advertisement != null && !advertisement.getNameSong().equals("")) {
            SetValueInView(advertisement.getNameSong(), advertisement.getImgSong(), advertisement.getImgAD());
            GetDataSongAD(advertisement.getIdAD());
        } else if (playlist != null && !playlist.getNamePlayList().equals("")) {
            SetValueInView(playlist.getNamePlayList(), playlist.getIconPlayList(), playlist.getIconPlayList());
            GetDataSongPlaylist(playlist.getIdPlayList().trim());
        } else if (category != null && !category.getNameCategory().equals("")) {
            SetValueInView(category.getNameCategory(), category.getImgCategory(), category.getImgCategory());
            GetDataSongCategory(category.getIdCategory());
        } else if (album != null && !album.getNameAlbum().equals("")) {
            SetValueInView(album.getNameAlbum(), album.getImgAlbum(), album.getImgAlbum());
            GetDataSongAlbum(album.getIdAlbum());
        }else if(rankSong!=null && !rankSong.getNameRankSong().trim().equals("")){
            SetValueInView(rankSong.getNameRankSong(),rankSong.getIconRankSong(),rankSong.getIconRankSong());
            GetDataSongRankSong(rankSong.getIdRankSong());
        }

        getLike();
        addEvent();
    }

    private void getLike() {
        if (playlist != null && !playlist.getNamePlayList().equals("")) {
            String sql="select * from PlaylistLike";
            boolean like=false;
            Cursor cursor= MainActivity.database.GetData(sql);
            while (cursor.moveToNext()){
                if(playlist.getIdPlayList().trim().equals(cursor.getString(0))){
                    like=true;
                    break;
                }
            }
            if(like){
                UnLikePlaylistSong.setVisibility(View.INVISIBLE);
                LikePlaylistSong.setVisibility(View.VISIBLE);
            }
            else {
                UnLikePlaylistSong.setVisibility(View.VISIBLE);
                LikePlaylistSong.setVisibility(View.INVISIBLE);
            }

        }
        else if (category != null && !category.getNameCategory().equals("")){

            String sql="select * from CategoryLike";
            boolean like=false;
            Cursor cursor= MainActivity.database.GetData(sql);
            while (cursor.moveToNext()){
                if(category.getIdCategory().trim().equals(cursor.getString(0))){
                    like=true;
                    break;
                }
            }
            if(like){
                UnLikePlaylistSong.setVisibility(View.INVISIBLE);
                LikePlaylistSong.setVisibility(View.VISIBLE);
            }
            else {
                UnLikePlaylistSong.setVisibility(View.VISIBLE);
                LikePlaylistSong.setVisibility(View.INVISIBLE);
            }

        }
        else if (album != null && !album.getNameAlbum().equals("")){

            String sql="select * from AlbumLike";
            boolean like=false;
            Cursor cursor= MainActivity.database.GetData(sql);
            while (cursor.moveToNext()){
                if(album.getIdAlbum().trim().equals(cursor.getString(0))){
                    like=true;
                    break;
                }
            }
            if(like){
                UnLikePlaylistSong.setVisibility(View.INVISIBLE);
                LikePlaylistSong.setVisibility(View.VISIBLE);
            }
            else {
                UnLikePlaylistSong.setVisibility(View.VISIBLE);
                LikePlaylistSong.setVisibility(View.INVISIBLE);
            }

        }
        else {
            UnLikePlaylistSong.setVisibility(View.INVISIBLE);
            LikePlaylistSong.setVisibility(View.INVISIBLE);
        }
    }


    private void GetDataSongAD(String IdAd) {
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService = APIService.getService();
        Call<ArrayList<Song>> callBack = dataService.GetDataSongAdvertisement(IdAd);
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {

                btnPlaylistRandom.setEnabled(false);
                songArrayList = response.body();
                txtSoLuongBaiHat.setText(songArrayList.size() + " bài hát-bởi Megatron Music");
                playlistSongAdapter = new PlaylistSongAdapter(PlaylistSongActivity.this, songArrayList);
                recyclerViewPlayList.setAdapter(playlistSongAdapter);



                progressBar.setVisibility(View.GONE);
                btnPlaylistRandom.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataSongPlaylist(String idPlayList) {
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService = APIService.getService();
        Call<ArrayList<Song>> callBack = dataService.GetDataSongPlaylist(idPlayList);
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {
                btnPlaylistRandom.setEnabled(false);



                songArrayList = response.body();
                txtSoLuongBaiHat.setText(songArrayList.size() + " bài hát-bởi Megatron Music");
                playlistSongAdapter = new PlaylistSongAdapter(PlaylistSongActivity.this, songArrayList);

                recyclerViewPlayList.setAdapter(playlistSongAdapter);


                progressBar.setVisibility(View.GONE);

                btnPlaylistRandom.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataSongCategory(String idCategory) {
        btnPlaylistRandom.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService = APIService.getService();
        Call<ArrayList<Song>> callBack = dataService.GetDataSongCategory(idCategory);
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {



                songArrayList = response.body();
                playlistSongAdapter = new PlaylistSongAdapter(PlaylistSongActivity.this, songArrayList);
                txtSoLuongBaiHat.setText(songArrayList.size() + " bài hát-bởi Megatron Music");
                recyclerViewPlayList.setAdapter(playlistSongAdapter);


                progressBar.setVisibility(View.GONE);
                btnPlaylistRandom.setEnabled(true);

            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataSongAlbum(String idAlbum) {
        btnPlaylistRandom.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService = APIService.getService();
        Call<ArrayList<Song>> callBack = dataService.GetDataSongAlbum(idAlbum);
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {

                songArrayList = response.body();
                playlistSongAdapter = new PlaylistSongAdapter(PlaylistSongActivity.this, songArrayList);
                txtSoLuongBaiHat.setText(songArrayList.size() + " bài hát-bởi Megatron Music");
                recyclerViewPlayList.setAdapter(playlistSongAdapter);


                progressBar.setVisibility(View.GONE);
                btnPlaylistRandom.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataSongRankSong(String idRankSong) {

        btnPlaylistRandom.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService=APIService.getService();
        Call<ArrayList<Song>> callBack=dataService.GetDataTopSong(idRankSong);
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {

                songArrayList=response.body();
                playlistSongAdapter = new PlaylistSongAdapter(PlaylistSongActivity.this, songArrayList);
                txtSoLuongBaiHat.setText(songArrayList.size() + " bài hát-bởi Megatron Music");
                recyclerViewPlayList.setAdapter(playlistSongAdapter);



                progressBar.setVisibility(View.GONE);
                btnPlaylistRandom.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });
    }

    private void SetValueInView(String nameAd, String imgSong, String imgNen) {
        collapsingToolbarLayout.setTitle(nameAd);
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(imgNen).into(imageView);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        Picasso.get().load(imgSong).into(imgPlaylistSong);

    }


    private void init() {

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);



    }

    private void remap() {
        progressBar=findViewById(R.id.progressBarPlaylistSong);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        toolbar = findViewById(R.id.toolBarNamePlaylist);

        btnPlaylistRandom = findViewById(R.id.btnPlayListRandom);
        imgPlaylistSong = findViewById(R.id.imgPlaylistSong);

        txtSoLuongBaiHat = findViewById(R.id.txtSoLuongBaiHat);
        UnLikePlaylistSong = findViewById(R.id.UnLikePlaylistSong);
        LikePlaylistSong = findViewById(R.id.LikePlaylistSong);

        songArrayList = new ArrayList<>();
        recyclerViewPlayList = findViewById(R.id.recyclerViewPlayList);
        recyclerViewPlayList.setLayoutManager(new LinearLayoutManager(PlaylistSongActivity.this, LinearLayoutManager.VERTICAL, false));


    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("idSongAD")) {
                advertisement = (Advertisement) intent.getSerializableExtra("idSongAD");
            } else if (intent.hasExtra("idPlaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("idPlaylist");

            } else if (intent.hasExtra("idCategory")) {
                category = (Category) intent.getSerializableExtra("idCategory");
            } else if (intent.hasExtra("idAlbum")) {
                album = (Album) intent.getSerializableExtra("idAlbum");
            }else if (intent.hasExtra("idRankSong")) {
                rankSong= (RankSong) intent.getSerializableExtra("idRankSong");
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_playlist_song, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_info:
                Toast.makeText(this, "Đã click vào menu info ", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvent() {
        btnPlaylistRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(songArrayList.size()>0){
                    MainActivity.songArrayList.clear();

                    Intent intent=new Intent(PlaylistSongActivity.this,PlaySongActivity.class);
                    intent.putExtra("arraySong",songArrayList);
                    intent.putExtra("nameList",collapsingToolbarLayout.getTitle());

                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                else {
                    Toast.makeText(PlaylistSongActivity.this, "Chưa có bài hát hết", Toast.LENGTH_SHORT).show();
                }
            }
        });
        UnLikePlaylistSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (playlist != null && !playlist.getNamePlayList().equals("")) {
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.SetUpdateLikePlaylist(playlist.getIdPlayList(), 1);
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                UnLikePlaylistSong.setVisibility(View.INVISIBLE);
                                LikePlaylistSong.setVisibility(View.VISIBLE);
                                MainActivity.database.InsertDataPlaylistLike("PlaylistLike",playlist.getIdPlayList(),playlist.getNamePlayList(),playlist.getImgPlayList(),playlist.getIconPlayList());
                                Toast t = Toast.makeText(PlaylistSongActivity.this, "Đã thêm " + playlist.getNamePlayList() + " vào thư viện ", Toast.LENGTH_SHORT);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast t1 = Toast.makeText(PlaylistSongActivity.this, "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER, 0, 0);
                            t1.show();
                        }
                    });

                }
                else if (category != null && !category.getNameCategory().equals("")) {
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.SetUpdateLikeCategory(category.getIdCategory(), 1);
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                UnLikePlaylistSong.setVisibility(View.INVISIBLE);
                                LikePlaylistSong.setVisibility(View.VISIBLE);
                                MainActivity.database.InsertDataCategoryLike("CategoryLike",category.getIdCategory(),category.getIdTheme(),category.getNameCategory(),category.getImgCategory());

                                Toast t = Toast.makeText(PlaylistSongActivity.this, "Đã thêm " + category.getNameCategory() + " vào thư viện ", Toast.LENGTH_SHORT);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast t1 = Toast.makeText(PlaylistSongActivity.this, "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER, 0, 0);
                            t1.show();
                        }
                    });

                }
                else if (album != null && !album.getNameAlbum().equals("")) {
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.SetUpdateLikeAlbum(album.getIdAlbum(), 1);
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                UnLikePlaylistSong.setVisibility(View.INVISIBLE);
                                LikePlaylistSong.setVisibility(View.VISIBLE);

                                MainActivity.database.InsertDataAlbumLike("AlbumLike",album.getIdAlbum(),album.getNameAlbum(),album.getSingerAlbum(),album.getImgAlbum());

                                Toast t = Toast.makeText(PlaylistSongActivity.this, "Đã thêm " + album.getNameAlbum() + " vào thư viện ", Toast.LENGTH_SHORT);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast t1 = Toast.makeText(PlaylistSongActivity.this, "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER, 0, 0);
                            t1.show();
                        }
                    });

                }

            }
        });

        LikePlaylistSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playlist != null && !playlist.getNamePlayList().equals("")) {
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.SetUpdateLikePlaylist(playlist.getIdPlayList(), -1);
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                UnLikePlaylistSong.setVisibility(View.VISIBLE);
                                LikePlaylistSong.setVisibility(View.INVISIBLE);
                                MainActivity.database.SetData("DELETE FROM PlaylistLike WHERE IdPlaylist='"+playlist.getIdPlayList().trim()+"' ");
                                Toast t = Toast.makeText(PlaylistSongActivity.this, "Đã gỡ " + playlist.getNamePlayList() + " khỏi thư viện ", Toast.LENGTH_SHORT);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast t1 = Toast.makeText(PlaylistSongActivity.this, "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER, 0, 0);
                            t1.show();
                        }
                    });

                }
                else if (category != null && !category.getNameCategory().equals("")) {
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.SetUpdateLikeCategory(category.getIdCategory(), -1);
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                UnLikePlaylistSong.setVisibility(View.VISIBLE);
                                LikePlaylistSong.setVisibility(View.INVISIBLE);

                                MainActivity.database.SetData("DELETE FROM CategoryLike WHERE IdCategory='"+category.getIdCategory().trim()+"' ");
                                Toast t = Toast.makeText(PlaylistSongActivity.this, "Đã gỡ " + category.getNameCategory() + " khỏi thư viện ", Toast.LENGTH_SHORT);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast t1 = Toast.makeText(PlaylistSongActivity.this, "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER, 0, 0);
                            t1.show();
                        }
                    });

                }
                else if (album != null && !album.getNameAlbum().equals("")) {
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.SetUpdateLikeAlbum(album.getIdAlbum(), -1);
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                UnLikePlaylistSong.setVisibility(View.VISIBLE);
                                LikePlaylistSong.setVisibility(View.INVISIBLE);

                                MainActivity.database.SetData("DELETE FROM AlbumLike WHERE IdAlbum='"+album.getIdAlbum().trim()+"' ");

                                Toast t = Toast.makeText(PlaylistSongActivity.this, "Đã gỡ " + album.getNameAlbum() + " khỏi thư viện ", Toast.LENGTH_SHORT);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                            Toast t1 = Toast.makeText(PlaylistSongActivity.this, "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER, 0, 0);
                            t1.show();
                        }
                    });

                }

            }
        });
    }


}




