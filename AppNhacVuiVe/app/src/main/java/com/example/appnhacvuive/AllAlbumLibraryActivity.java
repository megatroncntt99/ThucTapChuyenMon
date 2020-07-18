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

import com.example.Adapter.AllAlbumLibraryAdapter;
import com.example.Adapter.AllCategoryLibraryAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Album;
import com.example.Model.Category;

import java.util.ArrayList;

public class AllAlbumLibraryActivity extends AppCompatActivity {

    Toolbar toolbarAlbumLibrary;

    ListView lvAllAlbumLibrary;
    public ArrayList<Album> albumArrayList;
    public AllAlbumLibraryAdapter allAlbumLibraryAdapter;
    public LinearLayout linearLayoutNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album_library);
        remap();
        getDataIntent();
        init();
        addEvent();
    }

    private void remap() {
        albumArrayList=new ArrayList<>();
        toolbarAlbumLibrary=findViewById(R.id.toolbarAlbumLibrary);
        lvAllAlbumLibrary=findViewById(R.id.lvAllAlbumLibrary);
        linearLayoutNoData=findViewById(R.id.linearLayoutNoData);
    }

    private void getDataIntent() {

        albumArrayList.clear();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("albumLibrary")){
                albumArrayList= (ArrayList<Album>) intent.getSerializableExtra("albumLibrary");
            }
        }
        if(albumArrayList.size()==0){
            linearLayoutNoData.setVisibility(View.VISIBLE);
        }
        allAlbumLibraryAdapter=new AllAlbumLibraryAdapter(AllAlbumLibraryActivity.this,albumArrayList);
        lvAllAlbumLibrary.setAdapter(allAlbumLibraryAdapter);
        new FunctionDesign().setListViewHeightBasedOnChildren(lvAllAlbumLibrary);
    }

    private void init() {
        setSupportActionBar(toolbarAlbumLibrary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAlbumLibrary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Album ("+albumArrayList.size()+") ");
        toolbarAlbumLibrary.setTitleTextColor(Color.BLACK);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();
    }
    private void addEvent() {
        lvAllAlbumLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AllAlbumLibraryActivity.this, PlaylistSongActivity.class);
                intent.putExtra("idAlbum",albumArrayList.get(i));
                startActivity(intent);
            }
        });
    }
}
