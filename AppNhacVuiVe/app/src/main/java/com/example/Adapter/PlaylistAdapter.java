package com.example.Adapter;

import android.app.Activity;
import android.app.Dialog;
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

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    Activity context;
    ArrayList<Playlist> playlistArrayList;

    public PlaylistAdapter(Activity context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.row_item_playlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(playlistArrayList.get(position).getIconPlayList()).into(holder.imgPlaylist);
        holder.txtNamePlaylist.setText(playlistArrayList.get(position).getNamePlayList());
        holder.btnPlaylist.setOnClickListener(new View.OnClickListener() {
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

        ImageView imgPlaylist;
        RelativeLayout btnPlaylist;
        TextView txtNamePlaylist;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgPlaylist=itemView.findViewById(R.id.imgPlaylist);
            btnPlaylist=itemView.findViewById(R.id.btnPlaylist);
            txtNamePlaylist=itemView.findViewById(R.id.txtNamePlaylist);

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
