package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.AllSongLibraryAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Song;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class AllSongLibraryActivity extends AppCompatActivity {

    public Toolbar toolbarSongLibrary;
    FloatingActionButton btnPlaySongLibrary;
    ListView lvAllSongLibrary;
    public ArrayList<Song> songArrayList;
    ArrayList<Song> songs;
    public AllSongLibraryAdapter allSongLibraryAdapter;
    ImageView imgFilter;
    TextView txtSearchSongLibrary;
    public ScrollView scrollView;
    public LinearLayout linearLayoutNoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_song_library);
        remap();
        init();
        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();


    }

    private void loadData() {
        songArrayList.clear();
        Cursor cursor= MainActivity.database.GetData("SELECT * FROM SongLike");
        while (cursor.moveToNext()){
            songArrayList.add(new Song(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)));
        }
        Collections.reverse(songArrayList);
        if(songArrayList.size()>0){
            linearLayoutNoData.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            switch ( MainActivity.check){
                case 1:

                    imgFilter.setImageResource(R.drawable.ic_unfiltered);
                    break;
                case 2:
                    imgFilter.setImageResource(R.drawable.ic_filter);
                    Collections.sort(songArrayList,new FunctionDesign.SortSongAZ());
                    break;
                case 3:
                    imgFilter.setImageResource(R.drawable.ic_filter);
                    Collections.sort(songArrayList,new FunctionDesign.SortSongZA());

                    break;

            }
            allSongLibraryAdapter=new AllSongLibraryAdapter(AllSongLibraryActivity.this,songArrayList);
            lvAllSongLibrary.setAdapter(allSongLibraryAdapter);
            new FunctionDesign().setListViewHeightBasedOnChildren(lvAllSongLibrary);
        }
        else {
            linearLayoutNoData.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }

        getSupportActionBar().setTitle("Bài hát ("+songArrayList.size()+") ");
    }


    private void remap() {
        songArrayList = new ArrayList<>();
        songs=new ArrayList<>();
        toolbarSongLibrary = findViewById(R.id.toolbarSongLibrary);
        btnPlaySongLibrary = findViewById(R.id.btnPlaySongLibrary);
        lvAllSongLibrary = findViewById(R.id.lvAllSongLibrary);
        imgFilter = findViewById(R.id.imgFilter);
        txtSearchSongLibrary=findViewById(R.id.txtSearchSongLibrary);
        linearLayoutNoData=findViewById(R.id.linearLayoutNoData);
        scrollView=findViewById(R.id.scrollView);


    }

    private void init() {
        setSupportActionBar(toolbarSongLibrary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSongLibrary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });


        toolbarSongLibrary.setTitleTextColor(Color.BLACK);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();

    }

    private void addEvent() {
        lvAllSongLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.songArrayList.clear();
                Intent intent = new Intent(AllSongLibraryActivity.this, PlaySongActivity.class);
                intent.putExtra("idSong", songArrayList.get(i));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });
        btnPlaySongLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(songArrayList.size()>0){
                    MainActivity.songArrayList.clear();
                    Intent intent=new Intent(AllSongLibraryActivity.this,PlaySongActivity.class);
                    intent.putExtra("arraySong",songArrayList);
                    intent.putExtra("nameList","Các bài hát trong thư  viện");
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                else {
                    Toast.makeText(AllSongLibraryActivity.this, "Chưa cập nhật bài hát ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(AllSongLibraryActivity.this);
                bottomSheetDialog.setContentView(R.layout.dialog_sort_song);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                TextView txtDefault=bottomSheetDialog.findViewById(R.id.txtDefault);
                TextView txtAZ=bottomSheetDialog.findViewById(R.id.txtAZ);
                TextView txtZA=bottomSheetDialog.findViewById(R.id.txtZA);
                switch ( MainActivity.check){
                    case 1:
                        txtDefault.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_default,0,R.drawable.ic_check_black_24dp,0);
                        txtAZ.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_az,0,0,0);
                        txtZA.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_za,0,0,0);

                        break;
                    case 2:
                        txtDefault.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_default,0,0,0);
                        txtAZ.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_az,0,R.drawable.ic_check_black_24dp,0);
                        txtZA.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_za,0,0,0);
                        break;
                    case 3:
                        txtDefault.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_default,0,0,0);
                        txtAZ.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_az,0,0,0);
                        txtZA.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sort_za,0,R.drawable.ic_check_black_24dp,0);
                        break;
                }

                txtDefault.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imgFilter.setImageResource(R.drawable.ic_unfiltered);
                        MainActivity.check=1;
                        loadData();
                        bottomSheetDialog.dismiss();



                    }
                });
                txtAZ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imgFilter.setImageResource(R.drawable.ic_filter);

                        MainActivity.check=2;
                        Collections.sort(songArrayList,new FunctionDesign.SortSongAZ());
                        allSongLibraryAdapter.notifyDataSetChanged();
                        bottomSheetDialog.dismiss();

                    }
                });
                txtZA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.check=3;
                        imgFilter.setImageResource(R.drawable.ic_filter);
                        Collections.sort(songArrayList,new FunctionDesign.SortSongZA());
                        allSongLibraryAdapter.notifyDataSetChanged();
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog.show();
            }
        });
        txtSearchSongLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(AllSongLibraryActivity.this,SearchSongLibraryActivity.class);
               startActivity(intent);
            }
        });
    }


}
