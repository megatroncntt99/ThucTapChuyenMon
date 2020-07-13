package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Model.Album;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends BaseAdapter {
    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view=inflater.inflate(R.layout.row_item_all_album,null);
            viewHolder.imgAllAlbum=view.findViewById(R.id.imgAllAlbum);
            viewHolder.txtNameAllAlbum=view.findViewById(R.id.txtNameAllAlbum);
            viewHolder.txtNameSingerAlbum=view.findViewById(R.id.txtNameSingerAlbum);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Album album=albumArrayList.get(i);
        Picasso.get().load(album.getImgAlbum()).into(viewHolder.imgAllAlbum);
        viewHolder.txtNameAllAlbum.setText(album.getNameAlbum().trim());
        viewHolder.txtNameSingerAlbum.setText(album.getSingerAlbum());

        return view;
    }
    public  class ViewHolder{
        ImageView imgAllAlbum;
        TextView txtNameAllAlbum,txtNameSingerAlbum;
    }
}
