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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Album;
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

public class AllAlbumAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Activity context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public int getCount() {
        return albumArrayList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.row_item_all_album, null);
            viewHolder.imgAllAlbum = view.findViewById(R.id.imgAllAlbum);
            viewHolder.txtNameAllAlbum = view.findViewById(R.id.txtNameAllAlbum);
            viewHolder.txtNameSingerAlbum = view.findViewById(R.id.txtNameSingerAlbum);
            viewHolder.btnPlayAllAlbum = view.findViewById(R.id.btnPlayAllAlbum);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Album album = albumArrayList.get(i);
        Picasso.get().load(album.getImgAlbum()).into(viewHolder.imgAllAlbum);
        viewHolder.txtNameAllAlbum.setText(album.getNameAlbum().trim());
        viewHolder.txtNameSingerAlbum.setText(album.getSingerAlbum());
        viewHolder.btnPlayAllAlbum.setOnClickListener(new View.OnClickListener() {
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
                        Call<ArrayList<Song>> callBack = dataService.GetDataSongAlbum(albumArrayList.get(i).getIdAlbum());
                        callBack.enqueue(new Callback<ArrayList<Song>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {


                                ArrayList<Song>songArrayList = response.body();
                                dialog.dismiss();
                                if(songArrayList.size()>0){
                                    MainActivity.songArrayList.clear();
                                    Intent intent=new Intent(context, PlaySongActivity.class);
                                    intent.putExtra("arraySong",songArrayList);
                                    intent.putExtra("nameList",albumArrayList.get(i).getNameAlbum());

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

        return view;
    }

    public class ViewHolder {
        ImageView imgAllAlbum;
        TextView txtNameAllAlbum, txtNameSingerAlbum;
        RelativeLayout btnPlayAllAlbum;
    }
}
