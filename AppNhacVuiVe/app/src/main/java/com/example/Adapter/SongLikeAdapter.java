package com.example.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongLikeAdapter extends BaseAdapter {
    Context context;
    ArrayList<Song> songArrayList;

    public SongLikeAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }
    public void addItemSongLike(ArrayList<Song> allSongs){
        songArrayList.addAll(allSongs);
        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return songArrayList.size();
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
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item_song_like, null);

            viewHolder.imgSongLike = view.findViewById(R.id.imgSongLike);
            viewHolder.DislikeSong = view.findViewById(R.id.DislikeSong);
            viewHolder.LikeSong = view.findViewById(R.id.LikeSong);
            viewHolder.imgMenuSongLike = view.findViewById(R.id.imgMenuSongLike);
            viewHolder.txtNameSongLike = view.findViewById(R.id.txtNameSongLike);
            viewHolder.txtSingerSongLike = view.findViewById(R.id.txtSingerSongLike);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Song sl=songArrayList.get(i);


        Picasso.get().load(sl.getImgSong()).into(viewHolder.imgSongLike);

        viewHolder.txtNameSongLike.setText(sl.getNameSong().trim());
        viewHolder.txtSingerSongLike.setText(sl.getSinger());

        String sql="select * from SongLike";
        boolean like=false;
        Cursor cursor= MainActivity.database.GetData(sql);
        while (cursor.moveToNext()){
            if(sl.getIdSong().trim().equals(cursor.getString(0))){
                like=true;
                break;
            }
        }
        if(like){
            viewHolder.LikeSong.setVisibility(View.VISIBLE);
            viewHolder.DislikeSong.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.LikeSong.setVisibility(View.INVISIBLE);
            viewHolder.DislikeSong.setVisibility(View.VISIBLE);
        }

        viewHolder.LikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeSong(sl.getIdSong(),-1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){
                            viewHolder.LikeSong.setVisibility(View.INVISIBLE);
                            viewHolder.DislikeSong.setVisibility(View.VISIBLE);

                            MainActivity.database.SetData("DELETE FROM SongLike WHERE IdSong='"+sl.getIdSong()+"' ");
                            Toast t=Toast.makeText(context,"Đã gỡ "+sl.getNameSong()+" khỏi thư viện ",Toast.LENGTH_SHORT);
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


        viewHolder.DislikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeSong(sl.getIdSong(),1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){
                            viewHolder.LikeSong.setVisibility(View.VISIBLE);
                            viewHolder.DislikeSong.setVisibility(View.INVISIBLE);
                            MainActivity.database.SetData("insert into SongLike values('"+sl.getIdSong()+"') ");
                            Toast t=Toast.makeText(context,"Đã thêm "+sl.getNameSong()+" vào thư viện ",Toast.LENGTH_SHORT);
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

        viewHolder.imgMenuSongLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast t=Toast.makeText(context,"Đã click vào menu của bài hát",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
            }
        });

        return view;
    }

    public class ViewHolder {
        ImageView imgSongLike, DislikeSong, LikeSong, imgMenuSongLike;
        TextView txtNameSongLike, txtSingerSongLike;
    }
}
