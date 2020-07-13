package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Song;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListSongAdapter extends BaseAdapter {

    Context context;
    ArrayList<Song> songArrayList;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
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
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(itemView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView=inflater.inflate(R.layout.row_item_list_song,null);

            viewHolder.imgListSong=itemView.findViewById(R.id.imgListSong);
            viewHolder.txtNameSong=itemView.findViewById(R.id.txtNameSong);
            viewHolder.txtSinger=itemView.findViewById(R.id.txtSinger);

            itemView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) itemView.getTag();
        }

        Song song=songArrayList.get(i);
        Picasso.get().load(song.getImgSong()).into(viewHolder.imgListSong);
        viewHolder.txtNameSong.setText(song.getNameSong().trim());
        viewHolder.txtSinger.setText(song.getSinger().trim());

        return itemView;
    }

    public  class ViewHolder  {
        ImageView imgListSong;
        TextView txtNameSong,txtSinger;
    }
}
