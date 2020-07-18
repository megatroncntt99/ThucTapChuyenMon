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

import com.example.Model.Album;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllAlbumActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<Album> albumArrayList;

    public AlbumAdapter(Activity context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.row_item_album, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(albumArrayList.get(position).getImgAlbum()).into(holder.imgAlbum);
        holder.txtNameAlbum.setText(albumArrayList.get(position).getNameAlbum().trim());
        holder.txtNameSingerAB.setText(albumArrayList.get(position).getSingerAlbum().trim());
        holder.btnPlayAlbum.setOnClickListener(new View.OnClickListener() {
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
                        Call<ArrayList<Song>> callBack = dataService.GetDataSongAlbum(albumArrayList.get(position).getIdAlbum());
                        callBack.enqueue(new Callback<ArrayList<Song>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {


                                ArrayList<Song>songArrayList = response.body();
                                dialog.dismiss();
                                if(songArrayList.size()>0){
                                    MainActivity.songArrayList.clear();
                                    Intent intent=new Intent(context, PlaySongActivity.class);
                                    intent.putExtra("arraySong",songArrayList);
                                    intent.putExtra("nameList",albumArrayList.get(position).getNameAlbum());

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
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAlbum;
        RelativeLayout btnPlayAlbum;
        TextView txtNameAlbum, txtNameSingerAB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            btnPlayAlbum = itemView.findViewById(R.id.btnPlayAlbum);
            txtNameAlbum = itemView.findViewById(R.id.txtNameAlbum);
            txtNameSingerAB = itemView.findViewById(R.id.txtNameSingerAB);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaylistSongActivity.class);
                    intent.putExtra("idAlbum", albumArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
