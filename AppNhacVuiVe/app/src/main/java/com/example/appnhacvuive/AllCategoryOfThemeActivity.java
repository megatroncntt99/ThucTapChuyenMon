package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.Adapter.CategoryAdapter;
import com.example.Model.Category;
import com.example.Model.Theme;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoryOfThemeActivity extends AppCompatActivity {


    CoordinatorLayout coordinatorLayoutTheme;
    CollapsingToolbarLayout collapsingToolbarLayoutTheme;
    Toolbar toolBarNameTheme;
    RecyclerView recyclerViewCategoryOfTheme;
    ArrayList<Category> categoryArrayList;
    Theme theme;
    ProgressBar progressBar;


    CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category_of_theme);
        getDataIntent();
        remap();
        init();
        if(theme!=null&& !theme.getNameTheme().equals("")){
            SetValueInView(theme.getNameTheme(),theme.getImgTheme());
            GetData(theme.getIdTheme());
        }

    }

    private void GetData(String idTheme) {
        progressBar.setVisibility(View.VISIBLE);
        DataService dataService= APIService.getService();
        Call<ArrayList<Category>> callBack=dataService.GetDataCategoryOfTheme(idTheme);
        callBack.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {

                ArrayList<Category> categoryArrayList=response.body();
                categoryAdapter=new CategoryAdapter(AllCategoryOfThemeActivity.this,categoryArrayList);
                recyclerViewCategoryOfTheme.setAdapter(categoryAdapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

            }
        });
    }

    private void SetValueInView(String nameTheme, String imgTheme) {
        ImageView imageView=new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(imgTheme).into(imageView);
        collapsingToolbarLayoutTheme.setBackground(imageView.getDrawable());
        collapsingToolbarLayoutTheme.setTitle(nameTheme.toUpperCase());
    }


    private void getDataIntent() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("idTheme")){
                theme= (Theme) intent.getSerializableExtra("idTheme");
            }
        }
    }

    private void remap() {

        coordinatorLayoutTheme=findViewById(R.id.coordinatorLayoutTheme);
        collapsingToolbarLayoutTheme=findViewById(R.id.collapsingToolbarLayoutTheme);
        toolBarNameTheme=findViewById(R.id.toolBarNameTheme);
        recyclerViewCategoryOfTheme=findViewById(R.id.recyclerViewCategoryOfTheme);
        recyclerViewCategoryOfTheme.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        categoryArrayList=new ArrayList<>();

        progressBar=findViewById(R.id.progressBarAllCategory);

    }


    private void init() {
        setSupportActionBar(toolBarNameTheme);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolBarNameTheme.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayoutTheme.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayoutTheme.setCollapsedTitleTextColor(Color.WHITE);
    }


   

}
