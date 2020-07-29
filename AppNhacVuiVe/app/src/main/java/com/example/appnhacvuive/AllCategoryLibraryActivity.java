package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.Adapter.AllCategoryLibraryAdapter;
import com.example.Adapter.AllPlaylistLibraryAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Category;
import com.example.Model.Playlist;

import java.util.ArrayList;

public class AllCategoryLibraryActivity extends AppCompatActivity {

    Toolbar toolbarCategoryLibrary;

    ListView lvAllCategoryLibrary;
    public ArrayList<Category> categoryArrayList;
    public AllCategoryLibraryAdapter allCategoryLibraryAdapter;
    public LinearLayout linearLayoutNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category_library);
        remap();
        getDataIntent();
        init();
        addEvent();
    }

    private void remap() {
        categoryArrayList=new ArrayList<>();
        toolbarCategoryLibrary=findViewById(R.id.toolbarCategoryLibrary);
        lvAllCategoryLibrary=findViewById(R.id.lvAllCategoryLibrary);
        linearLayoutNoData=findViewById(R.id.linearLayoutNoData);
    }

    private void getDataIntent() {

        categoryArrayList.clear();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("categoryLibrary")){
                categoryArrayList= (ArrayList<Category>) intent.getSerializableExtra("categoryLibrary");
            }
        }
        if(categoryArrayList.size()==0){
            linearLayoutNoData.setVisibility(View.VISIBLE);
        }
        allCategoryLibraryAdapter=new AllCategoryLibraryAdapter(AllCategoryLibraryActivity.this,categoryArrayList);
        lvAllCategoryLibrary.setAdapter(allCategoryLibraryAdapter);
        new FunctionDesign().setListViewHeightBasedOnChildren(lvAllCategoryLibrary);
    }

    private void init() {
        setSupportActionBar(toolbarCategoryLibrary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCategoryLibrary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        getSupportActionBar().setTitle("Thể loại ("+categoryArrayList.size()+") ");

        toolbarCategoryLibrary.setTitleTextColor(Color.BLACK);
    }

    private void addEvent() {
        lvAllCategoryLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AllCategoryLibraryActivity.this, PlaylistSongActivity.class);
                intent.putExtra("idCategory",categoryArrayList.get(i));
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();
    }
}
