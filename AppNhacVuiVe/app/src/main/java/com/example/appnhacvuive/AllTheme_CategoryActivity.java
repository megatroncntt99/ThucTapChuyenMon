package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Adapter.AllThemeAdapter;
import com.example.Model.Theme;
import com.example.Service.APIService;
import com.example.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTheme_CategoryActivity extends AppCompatActivity {

    Toolbar toolbarTheme_Category;
    RecyclerView recyclerViewTheme;

    ArrayList<Theme> themeArrayList;

    AllThemeAdapter allThemeAdapter;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_theme__category);
        remap();
        init();
        GetData();
    }




    private void remap() {
        toolbarTheme_Category=findViewById(R.id.toolbarTheme_Category);
        recyclerViewTheme=findViewById(R.id.recyclerViewTheme);
        recyclerViewTheme.setLayoutManager(new GridLayoutManager(AllTheme_CategoryActivity.this,2));

        progressBar=findViewById(R.id.progressBarAllTheme);

    }

    private void init() {
        setSupportActionBar(toolbarTheme_Category);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chủ Đề Và Thể Loại");

            toolbarTheme_Category.setTitleTextColor(Color.BLACK);

            toolbarTheme_Category.setNavigationOnClickListener(new View.OnClickListener() {
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
        Call<ArrayList<Theme>> callBack=dataService.GetDataTheme();
        callBack.enqueue(new Callback<ArrayList<Theme>>() {
            @Override
            public void onResponse(Call<ArrayList<Theme>> call, Response<ArrayList<Theme>> response) {

                themeArrayList=response.body();
                allThemeAdapter=new AllThemeAdapter(AllTheme_CategoryActivity.this,themeArrayList);
                recyclerViewTheme.setAdapter(allThemeAdapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ArrayList<Theme>> call, Throwable t) {

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
            Intent intent = new Intent(AllTheme_CategoryActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
