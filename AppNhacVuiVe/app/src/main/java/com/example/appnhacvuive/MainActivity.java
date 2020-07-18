package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.MainViewPagerAdapter;
import com.example.Database.Database;
import com.example.Fragment.FragmentBXH;
import com.example.Fragment.FragmentMV;
import com.example.Fragment.FragmentHome;
import com.example.Model.KeyHot;
import com.example.Model.Song;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static ImageView imgAccount;
    ImageView imgMicro;
    TextView edtToSearch;
    SwipeRefreshLayout swipeRefreshLayout;
    public static MediaPlayer mediaPlaySong;
    public static ArrayList<Song> songArrayList = new ArrayList<>();
    public static int position = 0;
    public static int check=1;


    public static final int REQUEST_CODE_MICROPHONE = 1;
    public static final int REQUEST_CODE_STORAGE = 2;
    public static final int REQUEST_CODE_CALL = 3;
    public static final int REQUEST_CODE_STORAGE_CALL = 4;
    SharedPreferences sharedPreferencesFirst;
    public static Database database;

    long timeBack = 0;
    public static boolean load= false;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    TextView txtNetworkFailHome;
    LinearLayout linearLayout;
    FragmentHome fragmentHome;
    FragmentBXH fragmentBXH;
    FragmentMV fragmentMV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        sharedPreferencesFirst = getSharedPreferences("dataSave", MODE_PRIVATE);

        remap();
        createDatabase();
        if (!sharedPreferencesFirst.getBoolean("First", false)) {
            requestPermissionFile_CALL();
            database.SetData("Insert into SongNew values('1','Thích Thì Đến ', 'https://megtroncntt99.000webhostapp.com/image/imgSong/thichthiden.jpg', 'Lê Bảo Bình', 'https://vancntt99.000webhostapp.com/filemp3/thich_thi_den.mp3', 22)");
        }
        check=sharedPreferencesFirst.getInt("check",1);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!ConnectionReceiver.isConnected()) {
                    txtNetworkFailHome.setVisibility(View.VISIBLE);
                } else {
                    txtNetworkFailHome.setVisibility(View.GONE);
                }
                handler.postDelayed(this, 2000);
            }
        }, 1000);

        init();
//
//        MVMusicActivity.hideKeyboard(MainActivity.this);


        loadDataSong();
        getData();
        addEvent();
    }

    private void getData() {
        DataService dataService= APIService.getService();
        Call<ArrayList<KeyHot>> callBack=dataService.GetDataKeyHot();
        callBack.enqueue(new Callback<ArrayList<KeyHot>>() {
            @Override
            public void onResponse(Call<ArrayList<KeyHot>> call, Response<ArrayList<KeyHot>> response) {
                ArrayList<KeyHot> keyHots=response.body();
                if(keyHots.size()>0){
                    if(!sharedPreferencesFirst.getBoolean("First", false)){

                        SharedPreferences.Editor edit = sharedPreferencesFirst.edit();
                        edit.putBoolean("First", true);
                        edit.commit();
                        for(int i=0;i<keyHots.size()-1;i++){
                            database.SetData("Insert into keyHot values('"+keyHots.get(i).getIdKeyHot()+"','"+keyHots.get(i).getNameKeyHot()+"' )");

                        }
                    }
                    else {
                        if(keyHots.get(keyHots.size()-1).getNameKeyHot().trim().equals("insert")){
                            database.SetData("DELETE FROM keyHot");
                            for(int i=0;i<keyHots.size()-1;i++){
                                database.SetData("Insert into keyHot values('"+keyHots.get(i).getIdKeyHot()+"','"+keyHots.get(i).getNameKeyHot()+"' )");
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<KeyHot>> call, Throwable t) {

            }
        });
    }

    private void addEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                init();
                checkLoginStatus();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },4000);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        checkLoginStatus();
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor edit = sharedPreferencesFirst.edit();
        edit.putInt("position",position);
        edit.putInt("check",check);
        edit.commit();
    }

    private void loadDataSong() {
        Cursor cursor = database.GetData("Select * from SongNew");
        while (cursor.moveToNext()) {
            songArrayList.add(new Song(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)));
        }
        if(songArrayList.size()!=1){
           position=sharedPreferencesFirst.getInt("position",0);
        }
        else {
            position=0;
        }


    }


    public static void checkLoginStatus() {
        String sql = "select * from Account";
        Cursor cursor = MainActivity.database.GetData(sql);
        imgAccount.setImageResource(R.drawable.ic_person);
        while (cursor.moveToNext()) {
            Picasso.get().load(cursor.getString(0))
                    .placeholder(R.drawable.custom_load_image)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(imgAccount);
        }

    }

    public void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), 2);
        mainViewPagerAdapter.addFragment(fragmentHome, "Home");
        mainViewPagerAdapter.addFragment(fragmentBXH, "BXH");
        mainViewPagerAdapter.addFragment(fragmentMV, "MV");
        myViewPager.setAdapter(mainViewPagerAdapter);
        myTabLayout.setupWithViewPager(myViewPager);
        myTabLayout.getTabAt(0).setIcon(R.drawable.ic_homepage);
        myTabLayout.getTabAt(1).setIcon(R.drawable.ic_insert_chart);
        myTabLayout.getTabAt(2).setIcon(R.drawable.youtube);

        myViewPager.setCurrentItem(0);

        fragmentBXH = (FragmentBXH) mainViewPagerAdapter.getItem(1);
    }

    private void createDatabase() {
        database = new Database(MainActivity.this, "Music.sqLite", null, 1);
        database.SetData("create table if not exists SearchHistory(idKey nvarchar(255) )");
        database.SetData("create table if not exists SongLike(idSong nvarchar(255) ,nameSong nvarchar(255)," +
                " imgSong nvarchar(255),singer nvarchar(255),linkSong nvarchar(255),likeSong nvarchar(255))");
        database.SetData("create table if not exists PlaylistLike(IdPlaylist integer,namePlaylist nvarchar(255),imgPlayList nvarchar(255), iconPlaylist nvarchar(255))");
        database.SetData("create table if not exists CategoryLike(IdCategory integer ,IdTheme integer, nameCategory nvarchar(255), imgCategory nvarchar(255) )");
        database.SetData("create table if not exists AlbumLike(IdAlbum integer,nameAlbum nvarchar(255),singerAlbum nvarchar(255),imgAlbum nvarchar(255)  )");
        database.SetData("create table if not exists MVLike(IdMV integer ,keyMV nvarchar(255), imgMV nvarchar(255), imgSinger nvarchar(255) ,timeMV nvarchar(255), nameSong nvarchar(255),nameSinger nvarchar(255),likeMV nvarchar(255) )");
        database.SetData("create table if not exists Account(idAccount nvarchar(255),first_name nvarchar(255), last_name nvarchar(255),email nvarchar(255))");
        database.SetData("create table if not exists SongNew(idSong nvarchar(255) ,nameSong nvarchar(255)," +
                " imgSong nvarchar(255),singer nvarchar(255),linkSong nvarchar(255),likeSong nvarchar(255))");
        database.SetData("create table if not exists keyHot(idKeyHot integer,  nameKeyHot nvarchar(255) )");

    }





    public void requestPermissionAudio() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_MICROPHONE);

    }

    private void requestPermissionFile_CALL() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE}, REQUEST_CODE_STORAGE_CALL);
    }


    public void requestPermissionFile() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_STORAGE);
    }

    public void requestPermissionCall() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_MICROPHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Bạn đã từ chối quyền Ghi Âm Nên không thể thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }

                break;
            case REQUEST_CODE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Bạn đã từ chối quyền truy cập vào quản lý Nên không thể thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Bạn đã từ chối quyền truy cập vào phone Nên không thể thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void remap() {
        imgAccount = findViewById(R.id.imgAccount);
        imgMicro = findViewById(R.id.imgMicro);
        edtToSearch = findViewById(R.id.edtToSearch);


        myViewPager = findViewById(R.id.myViewPager);
        myTabLayout = findViewById(R.id.myTabLayout);
        txtNetworkFailHome = findViewById(R.id.txtNetworkFailHome);
        linearLayout = findViewById(R.id.LLHome1);
        fragmentHome = new FragmentHome();
        fragmentBXH = new FragmentBXH();
        fragmentMV = new FragmentMV();
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);

    }

    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.imgAccount:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
                break;
            case R.id.imgMicro:
                requestPermissionCall();
                break;
            case R.id.edtToSearch:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

                break;

        }
    }

    @Override
    public void onBackPressed() {

        if (timeBack + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast t = Toast.makeText(MainActivity.this, "Nhấn thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
        timeBack = System.currentTimeMillis();
    }




}
