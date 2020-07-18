package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.Adapter.AllPlaylistLibraryAdapter;
import com.example.Adapter.AllSongLibraryAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Playlist;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllPlaylistLibraryActivity extends AppCompatActivity {

    Toolbar toolbarPlaylistLibrary;

    ListView lvAllPlaylistLibrary;
    public LinearLayout linearLayoutNoData;
    public ArrayList<Playlist> playlistArrayList;
    public AllPlaylistLibraryAdapter allPlaylistLibraryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_playlist_library);
        remap();
        getDataIntent();
        init();
        addEvent();
    }




    private void remap() {
        playlistArrayList=new ArrayList<>();
        toolbarPlaylistLibrary=findViewById(R.id.toolbarPlaylistLibrary);
        lvAllPlaylistLibrary=findViewById(R.id.lvAllPlaylistLibrary);
        linearLayoutNoData=findViewById(R.id.linearLayoutNoData);
    }
    private void getDataIntent() {
        playlistArrayList.clear();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("playlistLibrary")){
                playlistArrayList= (ArrayList<Playlist>) intent.getSerializableExtra("playlistLibrary");
            }
        }
        if(playlistArrayList.size()==0){
            linearLayoutNoData.setVisibility(View.VISIBLE);
        }
        allPlaylistLibraryAdapter=new AllPlaylistLibraryAdapter(AllPlaylistLibraryActivity.this,playlistArrayList);
        lvAllPlaylistLibrary.setAdapter(allPlaylistLibraryAdapter);
        new FunctionDesign().setListViewHeightBasedOnChildren(lvAllPlaylistLibrary);
    }



    private void init() {
        setSupportActionBar(toolbarPlaylistLibrary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaylistLibrary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        getSupportActionBar().setTitle("Playlist ("+playlistArrayList.size()+") ");

        toolbarPlaylistLibrary.setTitleTextColor(Color.BLACK);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();
    }

    private void addEvent() {
        lvAllPlaylistLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(AllPlaylistLibraryActivity.this, PlaylistSongActivity.class);
                intent.putExtra("idPlaylist",playlistArrayList.get(i));
                startActivity(intent);
            }
        });
    }
}
