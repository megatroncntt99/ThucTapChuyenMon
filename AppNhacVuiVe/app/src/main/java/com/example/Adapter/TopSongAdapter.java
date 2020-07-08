package com.example.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.Model.RankSong;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSongAdapter extends BaseAdapter {
    Context context;
    ArrayList<RankSong> rankSongArrayList;



    public TopSongAdapter(Context context, ArrayList<RankSong> rankSongArrayList) {
        this.context = context;
        this.rankSongArrayList = rankSongArrayList;

    }

    @Override
    public int getCount() {
        return rankSongArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.row_item_top_song,null);

            viewHolder.imgTopSong=view.findViewById(R.id.imgTopSong);

            viewHolder.txtNameSongTop1=view.findViewById(R.id.txtNameSongTop1);
            viewHolder.txtSingerSongTop1=view.findViewById(R.id.txtSingerSongTop1);

            viewHolder.txtNameSongTop2=view.findViewById(R.id.txtNameSongTop2);
            viewHolder.txtSingerSongTop2=view.findViewById(R.id.txtSingerSongTop2);

            viewHolder.txtNameSongTop3=view.findViewById(R.id.txtNameSongTop3);
            viewHolder.txtSingerSongTop3=view.findViewById(R.id.txtSingerSongTop3);



            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        RankSong rankSong=rankSongArrayList.get(i);
        Picasso.get().load(rankSong.getIconRankSong()).into(viewHolder.imgTopSong);




        DataService dataService= APIService.getService();
        Call<ArrayList<Song>> callBack=dataService.GetDataTopSong(rankSong.getIdRankSong());
        callBack.enqueue(new Callback<ArrayList<Song>>() {
            @Override
            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {

                ArrayList<Song> songArrayList =response.body();
                if(songArrayList.size()>=3&& !songArrayList.get(2).getNameSong().equals("")){
                    viewHolder.txtNameSongTop1.setText(songArrayList.get(0).getNameSong());
                    viewHolder.txtSingerSongTop1.setText("- "+songArrayList.get(0).getSinger());

                    viewHolder.txtNameSongTop2.setText(songArrayList.get(1).getNameSong());
                    viewHolder.txtSingerSongTop2.setText("- "+songArrayList.get(1).getSinger());

                    viewHolder.txtNameSongTop3.setText(songArrayList.get(2).getNameSong());
                    viewHolder.txtSingerSongTop3.setText("- "+songArrayList.get(2).getSinger());
                }
                else {
                    viewHolder.txtNameSongTop1.setText("Chưa cập nhật");
                    viewHolder.txtSingerSongTop1.setText("- "+"Chưa cập nhật");

                    viewHolder.txtNameSongTop2.setText("Chưa cập nhật");
                    viewHolder.txtSingerSongTop2.setText("- "+"Chưa cập nhật");

                    viewHolder.txtNameSongTop3.setText("Chưa cập nhật");
                    viewHolder.txtSingerSongTop3.setText("- "+"Chưa cập nhật");
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {

            }
        });





        return view;
    }


    public class ViewHolder{
        ImageView imgTopSong;
        TextView txtNameSongTop1,txtSingerSongTop1,txtNameSongTop2,txtSingerSongTop2,txtNameSongTop3,txtSingerSongTop3;
    }




}
