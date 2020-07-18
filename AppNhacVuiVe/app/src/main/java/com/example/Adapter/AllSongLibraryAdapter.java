package com.example.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllSongLibraryActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllSongLibraryAdapter extends BaseAdapter {

    AllSongLibraryActivity context;
    ArrayList<Song> songArrayList;

    public AllSongLibraryAdapter(AllSongLibraryActivity context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;

    }

    @Override
    public int getCount() {
        return  songArrayList.size();
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
            view = inflater.inflate(R.layout.row_item_all_song_library, null);

            viewHolder.imgSongLike = view.findViewById(R.id.imgSongLibrary);

            viewHolder.LikeSong = view.findViewById(R.id.LikeSongLibrary);
            viewHolder.imgMenuSongLike = view.findViewById(R.id.imgMenuSongLibrary);
            viewHolder.txtNameSongLike = view.findViewById(R.id.txtNameSongLibrary);
            viewHolder.txtSingerSongLike = view.findViewById(R.id.txtSingerSongLibrary);

            view.setTag(viewHolder);
        } else {
           viewHolder= (ViewHolder) view.getTag();
        }
        final Song sl=songArrayList.get(i);



        Picasso.get().load(sl.getImgSong())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp).into(viewHolder.imgSongLike);

        viewHolder.txtNameSongLike.setText(sl.getNameSong().trim());
        viewHolder.txtSingerSongLike.setText(sl.getSinger());



        viewHolder.LikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.songArrayList.remove(sl);
                if(context.songArrayList.size()==0){
                    context.linearLayoutNoData.setVisibility(View.VISIBLE);
                    context.scrollView.setVisibility(View.GONE);
                }
                context.allSongLibraryAdapter.notifyDataSetChanged();
                context.getSupportActionBar().setTitle("Bài hát ("+context.songArrayList.size()+") ");
                MainActivity.database.SetData("DELETE FROM SongLike WHERE IdSong='"+sl.getIdSong()+"' ");
                Toast t=Toast.makeText(context,"Đã gỡ "+sl.getNameSong()+" khỏi thư viện ",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeSong(sl.getIdSong(),-1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

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
        ImageView imgSongLike, LikeSong, imgMenuSongLike;
        TextView txtNameSongLike, txtSingerSongLike;
    }
}
