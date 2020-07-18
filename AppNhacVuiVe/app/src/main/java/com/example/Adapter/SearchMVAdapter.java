package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Model.MVMusic;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMVAdapter extends BaseAdapter {
    Context context;
    ArrayList<MVMusic> mvMusicArrayList;

    public SearchMVAdapter(Context context, ArrayList<MVMusic> mvMusicArrayList) {
        this.context = context;
        this.mvMusicArrayList = mvMusicArrayList;
    }

    @Override
    public int getCount() {
        return mvMusicArrayList.size();
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
            view=inflater.inflate(R.layout.row_item_search_mv,null);
            viewHolder.imgMVSearch=view.findViewById(R.id.imgMVSearch);
            viewHolder.txtNameMVSearch=view.findViewById(R.id.txtNameMVSearch);
            viewHolder.txtNameSingerMVSearch=view.findViewById(R.id.txtNameSingerMVSearch);

            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        MVMusic mvMusic=mvMusicArrayList.get(i);
        Picasso.get().load(mvMusic.getImgSongMV())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(viewHolder.imgMVSearch);
        viewHolder.txtNameMVSearch.setText(mvMusic.getNameSong().trim());
        viewHolder.txtNameSingerMVSearch.setText(mvMusic.getNameSinger());

        return view;
    }
    public class ViewHolder{
        ImageView imgMVSearch;
        TextView txtNameMVSearch,txtNameSingerMVSearch;
    }
}
