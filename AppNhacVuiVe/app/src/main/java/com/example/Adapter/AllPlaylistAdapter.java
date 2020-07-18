package com.example.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Playlist;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlaylistAdapter extends RecyclerView.Adapter<AllPlaylistAdapter.ViewHolder> {
    Activity context;
    int layout;
    ArrayList<Playlist> playlistArrayList;

    public AllPlaylistAdapter(Activity context, int layout, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.layout = layout;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNameAllPlaylist.setText(playlistArrayList.get(position).getNamePlayList().trim());
        Picasso.get().load(playlistArrayList.get(position).getIconPlayList().trim())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imgAllPlaylist);
        holder.btnPlayAllPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.load_song);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DataService dataService = APIService.getService();
                        Call<ArrayList<Song>> callBack = dataService.GetDataSongPlaylist(playlistArrayList.get(position).getIdPlayList());
                        callBack.enqueue(new Callback<ArrayList<Song>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {

                                ArrayList<Song>songArrayList = response.body();
                                dialog.dismiss();
                                if(songArrayList.size()>0){
                                    MainActivity.songArrayList.clear();
                                    Intent intent=new Intent(context, PlaySongActivity.class);
                                    intent.putExtra("arraySong",songArrayList);
                                    intent.putExtra("nameList",playlistArrayList.get(position).getNamePlayList());
                                    context.startActivity(intent);
                                    context.overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                                }
                                else {
                                    Toast.makeText(context, "Chưa có bài hát nào hết", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Song>> call, Throwable t) {
                                dialog.dismiss();
                                Toast t1=Toast.makeText(context,"Không có kết nối mạng",Toast.LENGTH_SHORT);
                                t1.setGravity(Gravity.CENTER,0,0);
                                t1.show();
                            }
                        });
                        handler.removeCallbacks(this);
                    }
                },2000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAllPlaylist;
        TextView txtNameAllPlaylist;
        RelativeLayout btnPlayAllPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllPlaylist=itemView.findViewById(R.id.imgAllPlaylist);
            txtNameAllPlaylist=itemView.findViewById(R.id.txtNameAllPlaylist);
            btnPlayAllPlaylist=itemView.findViewById(R.id.btnPlayAllPlaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlaylistSongActivity.class);
                    intent.putExtra("idPlaylist",playlistArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
