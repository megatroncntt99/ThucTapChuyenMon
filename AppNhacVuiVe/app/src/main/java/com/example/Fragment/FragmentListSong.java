package com.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.Adapter.ListSongAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Interface.GetDataSong;
import com.example.Model.Song;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;


public class FragmentListSong extends Fragment {
    View viewListSong;

    ListView listViewListSong;
    TextView txtTotalSong,txtNameListSong;
    ListSongAdapter listSongAdapter;

    ImageView imgSongPlaying;
    TextView txtNameSongPlaying,txtSingerSongPlaying;
    LinearLayout linearLayout;
    GetDataSong getDataSong;
    ImageView imgPlaying;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewListSong=inflater.inflate(R.layout.fragment_list_song,container,false);
        
        remap();
        GetData();
        addEvent();
        return viewListSong;
    }

    private void addEvent() {
        listViewListSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getDataSong.getSong(i);
            }
        });


    }

    private void GetData() {

        if(PlaySongActivity.songArrayList.size()>0){
            Song song=PlaySongActivity.song;
            txtTotalSong.setText("Danh sách phát( "+PlaySongActivity.songArrayList.size()+" )");
            Picasso.get().load(song.getImgSong()).into(imgSongPlaying);
            txtNameSongPlaying.setText(song.getNameSong().trim());
            txtSingerSongPlaying.setText(song.getSinger().trim());

            if(PlaySongActivity.songArrayList.size()==1){
                linearLayout.setVisibility(View.GONE);

            }
            else if(PlaySongActivity.songArrayList.size()>1){

                linearLayout.setVisibility(View.VISIBLE);
                txtNameListSong.setText(PlaySongActivity.nameList);
                listSongAdapter=new ListSongAdapter(getActivity(),PlaySongActivity.songArrayList);
                listViewListSong.setAdapter(listSongAdapter);
            }
        }

    }

    private void remap() {
        getDataSong= (GetDataSong) getActivity();

        txtTotalSong=viewListSong.findViewById(R.id.txtTotalSong);
        txtNameListSong=viewListSong.findViewById(R.id.txtNameListSong);
        listViewListSong=viewListSong.findViewById(R.id.listViewListSong);
        new FunctionDesign().setListViewHeightBasedOnChildren(listViewListSong);

        txtNameSongPlaying=viewListSong.findViewById(R.id.txtNameSongPlaying);
        txtSingerSongPlaying=viewListSong.findViewById(R.id.txtSingerSongPlaying);
        imgPlaying=viewListSong.findViewById(R.id.imgPlaying);
        imgSongPlaying=viewListSong.findViewById(R.id.imgSongPlaying);
        linearLayout=viewListSong.findViewById(R.id.llRecyclerView);
    }

    public void setImagePlaying(){
        imgPlaying.setImageResource(R.drawable.playing);
    }
    public void setImagePause(){
        imgPlaying.setImageResource(R.drawable.playing1);
    }
}
