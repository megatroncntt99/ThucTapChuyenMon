package com.example.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistSongAdapter extends RecyclerView.Adapter<PlaylistSongAdapter.ViewHolder> {

    Activity context;
    ArrayList<Song>  songArrayList;


    public PlaylistSongAdapter(Activity context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_item_playlist_song,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Song song=songArrayList.get(position);

        holder.txtThuTuSong.setText(position+1+". ");
        Picasso.get().load(song.getImgSong()).into(holder.imgSong);
        holder.txtNameSong.setText(song.getNameSong());
        holder.txtSingerSong.setText(song.getSinger());
        String sql="select * from SongLike";
        boolean like=false;
        Cursor cursor= MainActivity.database.GetData(sql);
        while (cursor.moveToNext()){
            if(song.getIdSong().trim().equals(cursor.getString(0))){
                like=true;
                break;
            }
        }
        if(like){
            holder.LikeSong.setVisibility(View.VISIBLE);
            holder.DislikeSong.setVisibility(View.INVISIBLE);
        }
        else {
            holder.LikeSong.setVisibility(View.INVISIBLE);
            holder.DislikeSong.setVisibility(View.VISIBLE);
        }

        holder.LikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeSong(song.getIdSong(),-1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){
                            holder.LikeSong.setVisibility(View.INVISIBLE);
                            holder.DislikeSong.setVisibility(View.VISIBLE);
                            MainActivity.database.SetData("DELETE FROM SongLike WHERE IdSong='"+song.getIdSong()+"' ");
                            Toast t=Toast.makeText(context,"Đã gỡ "+song.getNameSong()+" khỏi thư viện ",Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER,0,0);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Toast t1=Toast.makeText(context,"Kiểm tra lại kết nối mạng",Toast.LENGTH_SHORT);
                        t1.setGravity(Gravity.CENTER,0,0);
                        t1.show();
                    }
                });

            }
        });


        holder.DislikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeSong(song.getIdSong(),1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){
                            holder.LikeSong.setVisibility(View.VISIBLE);
                            holder.DislikeSong.setVisibility(View.INVISIBLE);
                            MainActivity.database.InsertDataSongLike("SongLike",song.getIdSong(),song.getNameSong(),song.getImgSong(),song.getSinger(),song.getLinkSong(),song.getLikeSong());

                            Toast t=Toast.makeText(context,"Đã thêm "+song.getNameSong()+" vào thư viện ",Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER,0,0);
                            t.show();
                        }
                        else {

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast t1=Toast.makeText(context,"Kiểm tra lại kết nối mạng",Toast.LENGTH_SHORT);
                        t1.setGravity(Gravity.CENTER,0,0);
                        t1.show();
                    }
                });

            }
        });

        holder.imgMenuSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t=Toast.makeText(context,"Đã click vào menu của bài hát",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }


    public class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgSong, DislikeSong, LikeSong, imgMenuSong;
        TextView txtNameSong, txtSingerSong,txtThuTuSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSong = itemView.findViewById(R.id.imgSong);
            DislikeSong = itemView.findViewById(R.id.DislikeSong1);
            LikeSong = itemView.findViewById(R.id.LikeSong1);
            imgMenuSong = itemView.findViewById(R.id.imgMenuSong);
            txtNameSong = itemView.findViewById(R.id.txtNameSong);
            txtSingerSong = itemView.findViewById(R.id.txtSingerSong);
            txtThuTuSong=itemView.findViewById(R.id.txtThuTuSong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.songArrayList.clear();
                    Intent intent=new Intent(context, PlaySongActivity.class);
                    intent.putExtra("idSong",songArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                    context.overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
            });
        }
    }
}
