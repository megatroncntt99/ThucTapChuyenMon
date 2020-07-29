package com.example.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Adapter.SearchMVAdapter;
import com.example.Adapter.SongLikeAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.MVMusic;
import com.example.Model.Search;
import com.example.Model.Song;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MVMusicActivity;
import com.example.appnhacvuive.R;
import com.example.appnhacvuive.SearchActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSearchMV extends Fragment {

    View viewSearchMV;
    ListView lvSearchMV;
    ProgressBar progressBar;
    TextView txtNoDataSearchMV;
    SearchMVAdapter searchMVAdapter;
    String key="*";
    ArrayList<MVMusic> mvMusicArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewSearchMV = inflater.inflate(R.layout.fragment_search_m_v, container, false);
        remap();

        return viewSearchMV;

    }



    public void GetData() {

        progressBar.setVisibility(View.VISIBLE);
        txtNoDataSearchMV.setVisibility(View.GONE);
        DataService dataService= APIService.getService();
        Call<Search> callback=dataService.GetDataSearchSong(key);
        callback.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Search search=response.body();
                mvMusicArrayList= (ArrayList<MVMusic>) search.getMVMusic();
                if(mvMusicArrayList.size()>0){
                    txtNoDataSearchMV.setVisibility(View.GONE);

                    searchMVAdapter=new SearchMVAdapter(getActivity(),mvMusicArrayList);
                    lvSearchMV.setAdapter(searchMVAdapter);
                    FunctionDesign functionDesign=new FunctionDesign();
                    functionDesign.setListViewHeightBasedOnChildren(lvSearchMV);
                   addEvent();
                }
                else {
                    searchMVAdapter=new SearchMVAdapter(getActivity(),mvMusicArrayList);
                    lvSearchMV.setAdapter(searchMVAdapter);
                    txtNoDataSearchMV.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    private void addEvent() {
        lvSearchMV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), MVMusicActivity.class);
                intent.putExtra("KEY_MV",mvMusicArrayList.get(i).getKeyMV());
                startActivity(intent);

            }
        });
    }


    public void GetDataSearchMV(String keySearch) {
        if(ConnectionReceiver.isConnected()){
            key=keySearch;
        }else {

            progressBar.setVisibility(View.VISIBLE);
            txtNoDataSearchMV.setVisibility(View.GONE);
        }


    }

    private void remap() {
        progressBar=viewSearchMV.findViewById(R.id.progressBarSearchMV);
        lvSearchMV=viewSearchMV.findViewById(R.id.lvSearchMV);
        txtNoDataSearchMV=viewSearchMV.findViewById(R.id.txtNoDataSearchMV);
        mvMusicArrayList=new ArrayList<>();
    }
}
