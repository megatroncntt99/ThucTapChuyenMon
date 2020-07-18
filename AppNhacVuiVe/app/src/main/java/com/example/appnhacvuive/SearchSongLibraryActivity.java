package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Adapter.SongLikeAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Song;

import java.util.ArrayList;
import java.util.Collections;

public class SearchSongLibraryActivity extends AppCompatActivity {

    Toolbar toolbarSearchSongLibrary;
    EditText edtSearchSongLibrary;
    ListView lvSearchSongLibrary;

    public ArrayList<Song> songArrayList;
    public SongLikeAdapter songLikeAdapter;
    public static TextView txtNoResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song_library);
        remap();
        init();
        loadData();
        addEvent();



    }

    private void addEvent() {
        edtSearchSongLibrary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                songLikeAdapter.getFilter().filter(editable.toString().trim());
            }
        });
        lvSearchSongLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.songArrayList.clear();
                Intent intent=new Intent(SearchSongLibraryActivity.this, PlaySongActivity.class);
                intent.putExtra("idSong",songArrayList.get(i));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });
    }

    private void loadData() {
        songArrayList.clear();
        Cursor cursor= MainActivity.database.GetData(" SELECT * FROM SongLike ");
        while (cursor.moveToNext()){
            songArrayList.add(new Song(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)));
        }
        if(songArrayList.size()>0){
            txtNoResult.setVisibility(View.GONE);
            Collections.reverse(songArrayList);

        }
        else {
            txtNoResult.setVisibility(View.VISIBLE);
        }
        songLikeAdapter=new SongLikeAdapter(SearchSongLibraryActivity.this,songArrayList);
        lvSearchSongLibrary.setAdapter(songLikeAdapter);


    }

    private void init() {
        setSupportActionBar(toolbarSearchSongLibrary);
    }

    private void remap() {
        toolbarSearchSongLibrary=findViewById(R.id.toolbarSearchSongLibrary);
        edtSearchSongLibrary=findViewById(R.id.edtSearchSongLibrary);
        songArrayList=new ArrayList<>();
        lvSearchSongLibrary=findViewById(R.id.lvSearchSongLibrary);
        txtNoResult=findViewById(R.id.txtNoResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_close){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
