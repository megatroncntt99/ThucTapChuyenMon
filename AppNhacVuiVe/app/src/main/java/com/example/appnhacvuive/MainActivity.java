package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
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
import com.example.NetworkCheck.ConnectionReceiver;
import com.facebook.AccessToken;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    public static ImageView imgAccount;
    ImageView  imgMicro;
    EditText edtToSearch;
    public  static MediaPlayer mediaPlaySong;

    public static final int REQUEST_CODE_MICROPHONE = 1;
    public static final int REQUEST_CODE_STORAGE = 2;
    public static final int REQUEST_CODE_CALL = 3;
    public static final int REQUEST_CODE_STORAGE_CALL = 4;
    SharedPreferences sharedPreferencesFirst;
    public static Database database;
    long timeBack = 0;

    ViewPager myViewPager;
    TabLayout myTabLayout;
    ProgressBar progressBar;
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
        sharedPreferencesFirst = getSharedPreferences("First", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferencesFirst.edit();

        if (!sharedPreferencesFirst.getBoolean("First", false)) {
            requestPermissionFile_CALL();
        }

        remap();
        createDatabase();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!ConnectionReceiver.isConnected()) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
                handler.postDelayed(this, 2000);
            }
        }, 1000);

        init();

        MVMusicActivity.hideKeyboard(MainActivity.this);

        edit.putBoolean("First", true);
        edit.commit();



    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoginStatus();
    }

    public static void checkLoginStatus() {
        String sql = "select * from Account";
        Cursor cursor = MainActivity.database.GetData(sql);
        imgAccount.setImageResource(R.drawable.ic_person);
        while (cursor.moveToNext()) {
            Picasso.get().load(cursor.getString(0))
                    .placeholder(R.drawable.custom_progress_bar)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(imgAccount);
        }

    }


    private void init() {
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
        myTabLayout.setOnTabSelectedListener(MainActivity.this);

    }

    private void createDatabase() {

        database = new Database(MainActivity.this, "Music.sqLite", null, 1);
        database.SetData("create table if not exists SearchHistory(idKey nvarchar(255) primary key)");
        database.SetData("create table if not exists SongLike(IdSong integer primary key)");
        database.SetData("create table if not exists PlaylistLike(IdPlaylist integer primary key)");
        database.SetData("create table if not exists CategoryLike(IdCategory integer primary key)");
        database.SetData("create table if not exists AlbumLike(IdAlbum integer primary key)");
        database.SetData("create table if not exists Account(idAccount nvarchar(255) primary key,first_name nvarchar(255), last_name nvarchar(255),email nvarchar(255))");
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
        progressBar = findViewById(R.id.progressBarHome);
        linearLayout = findViewById(R.id.LLHome1);
        fragmentHome = new FragmentHome();
        fragmentBXH = new FragmentBXH();
        fragmentMV = new FragmentMV();

    }

    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.imgAccount:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 1:
                fragmentBXH.GetData();

                break;
        }

    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


}
