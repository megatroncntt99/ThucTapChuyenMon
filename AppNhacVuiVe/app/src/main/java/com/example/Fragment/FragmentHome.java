package com.example.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.Adapter.MainViewPagerAdapter;
import com.example.NetworkCheck.ConnectionReceiver;

import com.example.appnhacvuive.R;
import com.google.android.material.tabs.TabLayout;



public class FragmentHome extends Fragment {
    View viewHome;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    TextView txtNetworkFail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewHome = inflater.inflate(R.layout.fragment_home, container, false);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        remap();
        checkNetwork();
        addEvent();


        return viewHome;

    }

    private void addEvent() {

        txtNetworkFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNetwork();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!ConnectionReceiver.isConnected()) {
            txtNetworkFail.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void checkNetwork() {
        if (ConnectionReceiver.isConnected()) {

            init();
            txtNetworkFail.setVisibility(View.GONE);
            linearLayout.setBackgroundColor(android.R.color.transparent);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            },4000);

        } else {
            txtNetworkFail.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void init() {

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getFragmentManager(), 3);
        mainViewPagerAdapter.addFragment(new FragmentTrangChu(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new FragmentBXH(),"BXH");
        mainViewPagerAdapter.addFragment(new FragmentMV(),"MV");
        myViewPager.setAdapter(mainViewPagerAdapter);
        myTabLayout.setupWithViewPager(myViewPager);
        myTabLayout.getTabAt(0).setIcon(R.drawable.ic_homepage);
        myTabLayout.getTabAt(1).setIcon(R.drawable.ic_insert_chart);
        myTabLayout.getTabAt(2).setIcon(R.drawable.youtube);
    }

    private void remap() {
        myViewPager = viewHome.findViewById(R.id.myViewPager);
        myTabLayout = viewHome.findViewById(R.id.myTabLayout);
        progressBar = viewHome.findViewById(R.id.progressBarHome);
        linearLayout = viewHome.findViewById(R.id.LLHome1);
        txtNetworkFail = viewHome.findViewById(R.id.txtNetworkFail);
    }


}
