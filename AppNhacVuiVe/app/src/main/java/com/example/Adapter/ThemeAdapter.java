package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Model.Theme;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThemeAdapter extends BaseAdapter {
    Context context;
    ArrayList<Theme> themeArrayList;

    public ThemeAdapter(Context context, ArrayList<Theme> themeArrayList) {
        this.context = context;
        this.themeArrayList = themeArrayList;
    }

    @Override
    public int getCount() {
        return themeArrayList.size();
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
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.row_item_theme,null);

            viewHolder.imgTheme=view.findViewById(R.id.imgTheme);
            viewHolder.txtNameTheme=view.findViewById(R.id.txtNameTheme);
            view.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) view.getTag();

        }
        Theme theme=themeArrayList.get(i);
        Picasso.get().load(theme.getImgTheme()).into(viewHolder.imgTheme);
        viewHolder.txtNameTheme.setText(theme.getNameTheme().trim().toUpperCase());
        return view;
    }
    public  class ViewHolder{
        ImageView imgTheme;
        TextView txtNameTheme;
    }

}
