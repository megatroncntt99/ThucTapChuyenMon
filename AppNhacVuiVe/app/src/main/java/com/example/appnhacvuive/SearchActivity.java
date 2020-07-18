package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.MainViewPagerAdapter;
import com.example.Adapter.ThemeAdapter;
import com.example.Fragment.FragmentSearchMV;
import com.example.Fragment.FragmentSearchPlaylist;
import com.example.Fragment.FragmentSearchSong;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Theme;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.google.android.material.tabs.TabLayout;
import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    public static EditText edtSearch;
    TextView txtClearHistory, txtNetworkFailSearch;
    ImageView imgMicro_or_Delete, imgBack;

    TabLayout tabLayoutSearch;
    ViewPager viewPagerSearch;
    RelativeLayout relativeLayoutHistory;
    LinearLayout linearLayoutSearch;
    FlowLayout flowLayoutSearchHistory,flowLayoutKeyHot;
    ArrayList<String> searchHistoryArrayList;
    ArrayList<String> keyHotArrayList;
    ScrollView scrollViewSearch;
    FragmentSearchSong fragmentSearchSong;
    FragmentSearchMV fragmentSearchMV;
    FragmentSearchPlaylist fragmentSearchPlaylist;
    MainViewPagerAdapter mainViewPagerAdapter;
    ThemeAdapter themeAdapter;
    ArrayList<Theme> themeArrayList = new ArrayList<>();


    TextView txtTitleTheme, txtSeeMoreThemeSearch;
    GridView gridViewThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        remap();
        addFragment();
        GetData();
        addEvent();
        addSearchHistory();
        addKeyHot();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkNetwork();
                handler.postDelayed(this, 2000);
            }
        }, 1000);
    }



    private void GetData() {
        DataService dataService = APIService.getService();
        Call<ArrayList<Theme>> callBack = dataService.GetData6Theme();
        callBack.enqueue(new Callback<ArrayList<Theme>>() {
            @Override
            public void onResponse(Call<ArrayList<Theme>> call, Response<ArrayList<Theme>> response) {
                themeArrayList = response.body();
                themeAdapter = new ThemeAdapter(SearchActivity.this, themeArrayList);
                gridViewThem.setAdapter(themeAdapter);
                new FunctionDesign().setGridViewHeightBasedOnChildren(gridViewThem, 2);

            }

            @Override
            public void onFailure(Call<ArrayList<Theme>> call, Throwable t) {

            }
        });
    }

    private void addFragment() {
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), 2);

        mainViewPagerAdapter.addFragment(fragmentSearchSong, "Bài hát");
        mainViewPagerAdapter.addFragment(fragmentSearchMV, "MV");
        mainViewPagerAdapter.addFragment(fragmentSearchPlaylist, "Play list");
        viewPagerSearch.setAdapter(mainViewPagerAdapter);
        tabLayoutSearch.setupWithViewPager(viewPagerSearch);

        viewPagerSearch.setCurrentItem(1);

    }

    private void checkNetwork() {
        if (!ConnectionReceiver.isConnected()) {
            txtNetworkFailSearch.setVisibility(View.VISIBLE);
        } else {
            txtNetworkFailSearch.setVisibility(View.GONE);
        }
    }

    private void addSearchHistory() {
        if(flowLayoutSearchHistory==null){
            return;
        }
        flowLayoutSearchHistory.removeAllViews();
        loadHistorySearch();


        if(searchHistoryArrayList!=null&&searchHistoryArrayList.size()>0){
            relativeLayoutHistory.setVisibility(View.VISIBLE);
            for(int i=0;i<searchHistoryArrayList.size();i++){
                final TextView textView=new TextView(this);
                FlowLayout.LayoutParams params=new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,FlowLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,10,20,10);
                textView.setLayoutParams(params);
                textView.setText(searchHistoryArrayList.get(i));
                textView.setPadding(30,10,30,10);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.custom_txt_search);
                textView.setTextColor(getResources().getColor(R.color.colorText));
                flowLayoutSearchHistory.addView(textView);
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtSearch.setText(searchHistoryArrayList.get(finalI).trim());
                        edtSearch.setSelection(edtSearch.getText().toString().trim().length());
                    }
                });

            }

        }
        else {
            relativeLayoutHistory.setVisibility(View.GONE);
        }

    }

    private void addKeyHot() {
        keyHotArrayList.clear();
        Cursor cursor=MainActivity.database.GetData("SELECT * FROM keyHot");
        while (cursor.moveToNext()){
            keyHotArrayList.add(cursor.getString(1));
        }
        flowLayoutKeyHot.removeAllViews();
        if(keyHotArrayList!=null && keyHotArrayList.size()>0){

            for (int i=0;i<keyHotArrayList.size();i++){
                final TextView textView=new TextView(this);
                FlowLayout.LayoutParams params=new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT,FlowLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,10,20,10);
                textView.setLayoutParams(params);
                textView.setText(keyHotArrayList.get(i));
                textView.setPadding(30,10,30,10);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.custom_txt_search);
                textView.setTextColor(getResources().getColor(R.color.colorText));
                flowLayoutKeyHot.addView(textView);
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtSearch.setText(keyHotArrayList.get(finalI).trim());
                        edtSearch.setSelection(edtSearch.getText().toString().trim().length());
                    }
                });
            }
        }

    }

    private void loadHistorySearch() {
        searchHistoryArrayList.clear();
        String sql="select * from SearchHistory";
        Cursor cursor=MainActivity.database.GetData(sql);
        while (cursor.moveToNext()){
            searchHistoryArrayList.add(cursor.getString(0));
        }
    }

    private void remap() {
        scrollViewSearch = findViewById(R.id.scrollViewSearch);
        txtNetworkFailSearch = findViewById(R.id.txtNetworkFailSearch);
        tabLayoutSearch = findViewById(R.id.tabLayoutSearch);
        viewPagerSearch = findViewById(R.id.viewPagerSearch);
        edtSearch = findViewById(R.id.edtSearch);
        imgMicro_or_Delete = findViewById(R.id.imgMicro_Delete);
        imgBack = findViewById(R.id.imgBack);
        imgMicro_or_Delete.setTag(1);
        linearLayoutSearch = findViewById(R.id.linearLayoutSearch);
        relativeLayoutHistory = findViewById(R.id.relativeLayoutHistory);

        flowLayoutSearchHistory = findViewById(R.id.flowLayoutSearchHistory);
        flowLayoutKeyHot = findViewById(R.id.flowLayoutKeyHot);
        searchHistoryArrayList = new ArrayList<>();
        keyHotArrayList = new ArrayList<>();
        txtClearHistory = findViewById(R.id.txtClearHistory);

        fragmentSearchSong = new FragmentSearchSong();
        fragmentSearchMV = new FragmentSearchMV();
        fragmentSearchPlaylist = new FragmentSearchPlaylist();

        txtTitleTheme = findViewById(R.id.txtTitleTheme);
        txtSeeMoreThemeSearch = findViewById(R.id.txtSeeMoreThemeSearch);
        gridViewThem = findViewById(R.id.gridViewThem);

    }


    private void addEvent() {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MVMusicActivity.hideKeyboard(SearchActivity.this);
                finish();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ConnectionReceiver.isConnected()) {
                    if (editable.toString().trim().length()== 0) {
                        imgMicro_or_Delete.setImageResource(R.drawable.ic_micro);
                        imgMicro_or_Delete.setTag(1);
                        addSearchHistory();
                    } else {
                        imgMicro_or_Delete.setImageResource(R.drawable.ic_clear_black_24dp);
                        imgMicro_or_Delete.setTag(2);
                    }
                    if (editable.toString().trim().length()> 3) {
                        linearLayoutSearch.setVisibility(View.VISIBLE);
                        scrollViewSearch.setVisibility(View.GONE);

                        if (mainViewPagerAdapter.getCount() > 2) {

                            fragmentSearchSong = (FragmentSearchSong) mainViewPagerAdapter.getItem(0);
                            fragmentSearchMV = (FragmentSearchMV) mainViewPagerAdapter.getItem(1);
                            fragmentSearchPlaylist = (FragmentSearchPlaylist) mainViewPagerAdapter.getItem(2);

                            viewPagerSearch.setCurrentItem(0);


                            fragmentSearchSong.GetDataSearchSong(editable.toString().trim().toLowerCase());
                            fragmentSearchMV.GetDataSearchMV(editable.toString().trim().toLowerCase());
                            fragmentSearchPlaylist.GetDataSearchPlaylist(editable.toString().trim().toLowerCase());

                        }
                    } else {
                        linearLayoutSearch.setVisibility(View.INVISIBLE);
                        scrollViewSearch.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    linearLayoutSearch.setVisibility(View.INVISIBLE);
                    scrollViewSearch.setVisibility(View.VISIBLE);
                }


            }
        });

        imgMicro_or_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((int) imgMicro_or_Delete.getTag() == 1) {
                    Toast.makeText(SearchActivity.this, "Ghi âm", Toast.LENGTH_SHORT).show();
                } else if ((int) imgMicro_or_Delete.getTag() == 2) {
                    edtSearch.setText("");
                }
            }
        });




        txtClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(SearchActivity.this);
                dialog.setContentView(R.layout.dialog_clear_history_search);
                dialog.setCanceledOnTouchOutside(true);
                TextView txtDeleteHistory,txtCancelDelete;
                txtDeleteHistory=dialog.findViewById(R.id.txtDeleteHistory);
                txtCancelDelete=dialog.findViewById(R.id.txtCancelDelete);
                txtDeleteHistory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.database.SetData("DELETE FROM SearchHistory");
                        addSearchHistory();
                        dialog.dismiss();
                    }
                });
                txtCancelDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        gridViewThem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this, AllCategoryOfThemeActivity.class);
                intent.putExtra("idTheme", themeArrayList.get(i));
                startActivity(intent);
            }
        });
        txtTitleTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, AllTheme_CategoryActivity.class);
                startActivity(intent);
            }
        });
        txtSeeMoreThemeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, AllTheme_CategoryActivity.class);
                startActivity(intent);
            }
        });
        tabLayoutSearch.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                fragmentSearchSong.GetData();
                break;
            case 1:
                fragmentSearchMV.GetData();
                InsertHistorySearch();
                break;
            case 2:
                fragmentSearchPlaylist.GetData();
                InsertHistorySearch();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public static void InsertHistorySearch(){

        String sql="select * from SearchHistory";
        boolean check=true;
        Cursor cursor=MainActivity.database.GetData(sql);
        while (cursor.moveToNext()){
            if(cursor.getString(0).equals(edtSearch.getText().toString().trim().toLowerCase())){
                check=false;
                break;
            }
        }
       if(check){
           MainActivity.database.SetData("INSERT INTO `SearchHistory` (`idKey`) VALUES ('"+edtSearch.getText().toString().trim().toLowerCase()+"')");
       }

    }



}


