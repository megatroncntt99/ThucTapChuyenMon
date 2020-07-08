package com.example.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.Adapter.AdvertisementAdapter;
import com.example.Adapter.MainViewPagerAdapter;
import com.example.Model.Advertisement;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentQuangCao extends Fragment {
    View viewBanner;
    ViewPager viewPager;
    AdvertisementAdapter advertisementAdapter;


    CircleIndicator circleIndicator;
    int currentItem;

    int Pager=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewBanner=inflater.inflate(R.layout.fragment_quangcao,container,false);
        remap();
        getData();
        return viewBanner;
    }

    private void remap() {
        viewPager=viewBanner.findViewById(R.id.myViewPagerBanner);
        circleIndicator=viewBanner.findViewById(R.id.IndicatorBanner);
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<ArrayList<Advertisement>> callBack = dataService.GetDataAdvertisement();
        callBack.enqueue(new Callback<ArrayList<Advertisement>>() {
            @Override
            public void onResponse(Call<ArrayList<Advertisement>> call, Response<ArrayList<Advertisement>> response) {
                ArrayList<Advertisement> advertisementArrayList=response.body();
                advertisementAdapter=new AdvertisementAdapter(getContext(),advertisementArrayList);
                viewPager.setAdapter(advertisementAdapter);
                circleIndicator.setViewPager(viewPager);
                Pager=advertisementArrayList.size();


                updateBanner();

            }

            @Override
            public void onFailure(Call<ArrayList<Advertisement>> call, Throwable t) {
                Pager=0;
            }
        });
    }

    private void updateBanner() {
       final Handler handler=new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               currentItem=viewPager.getCurrentItem();
               currentItem++;
               if(currentItem>=Pager){
                   currentItem=0;
               }
               viewPager.setCurrentItem(currentItem,true);

               handler.postDelayed(this,5000);
           }
       },5000);
    }
}
