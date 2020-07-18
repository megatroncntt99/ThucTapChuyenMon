package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Adapter.AllAlbumLibraryAdapter;
import com.example.Adapter.AllMVLibraryAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Album;
import com.example.Model.MVMusic;
import com.example.NetworkCheck.ConnectionReceiver;

import java.util.ArrayList;

public class AllMVLibraryActivity extends AppCompatActivity {

    Toolbar toolbarMVLibrary;

    ListView lvAllMVLibrary;
    public LinearLayout linearLayoutNoData;
    public ArrayList<MVMusic> mvMusicArrayList;
    public AllMVLibraryAdapter allMVLibraryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_m_v_library);
        remap();
        getDataIntent();
        init();
        addEvent();
    }

    private void remap() {
        mvMusicArrayList=new ArrayList<>();
        toolbarMVLibrary=findViewById(R.id.toolbarMVLibrary);
        lvAllMVLibrary=findViewById(R.id.lvAllMVLibrary);
        linearLayoutNoData=findViewById(R.id.linearLayoutNoData);
    }

    private void getDataIntent() {
        mvMusicArrayList.clear();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("mvLibrary")){
                mvMusicArrayList= (ArrayList<MVMusic>) intent.getSerializableExtra("mvLibrary");
            }
        }
        if(mvMusicArrayList.size()==0){
            linearLayoutNoData.setVisibility(View.VISIBLE);
        }
        allMVLibraryAdapter=new AllMVLibraryAdapter(AllMVLibraryActivity.this,mvMusicArrayList);
        lvAllMVLibrary.setAdapter(allMVLibraryAdapter);
        new FunctionDesign().setListViewHeightBasedOnChildren(lvAllMVLibrary);
    }

    private void init() {
        setSupportActionBar(toolbarMVLibrary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMVLibrary.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("MV ("+mvMusicArrayList.size()+") ");
        toolbarMVLibrary.setTitleTextColor(Color.BLACK);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_from_left,R.anim.anim_to_right);
        finish();
    }

    private void addEvent() {
        lvAllMVLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialog=new Dialog(AllMVLibraryActivity.this);
                dialog.setContentView(R.layout.load_song);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if(ConnectionReceiver.isConnected()){
                            Intent intent=new Intent(AllMVLibraryActivity.this, MVMusicActivity.class);
                            intent.putExtra("KEY_MV",mvMusicArrayList.get(i).getKeyMV());
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                        }
                        else {
                            Toast t1=Toast.makeText(AllMVLibraryActivity.this,"Không có kết nối mạng",Toast.LENGTH_SHORT);
                            t1.setGravity(Gravity.CENTER,0,0);
                            t1.show();
                        }
                        handler.removeCallbacks(this);
                    }
                },2000);
            }
        });
    }
}
