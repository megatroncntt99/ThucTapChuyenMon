package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.Model.Advertisement;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdvertisementAdapter extends PagerAdapter {
    Context context;
    ArrayList<Advertisement> advertisementArrayList;

    public AdvertisementAdapter(Context context, ArrayList<Advertisement> advertisementArrayList) {
        this.context = context;
        this.advertisementArrayList = advertisementArrayList;
    }

    @Override
    public int getCount() {
        return advertisementArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item_ad, null);

        ImageView imgAD, imgADMini;
        TextView txtNameSongAD, txtContentAD;

        imgAD = view.findViewById(R.id.imgAD);
        imgADMini = view.findViewById(R.id.imgADMini);


        txtNameSongAD = view.findViewById(R.id.txtNameSongAD);
        txtContentAD = view.findViewById(R.id.txtContentAD);

        Picasso.get().load(advertisementArrayList.get(position).getImgAD()).into(imgAD);
        Picasso.get().load(advertisementArrayList.get(position).getImgSong()).into(imgADMini);

        txtNameSongAD.setText(advertisementArrayList.get(position).getNameSong());
        txtContentAD.setText(advertisementArrayList.get(position).getContentAD());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(advertisementArrayList.get(position).getIdAD())==3){
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(advertisementArrayList.get(position).getLinkSong()));
                    context.startActivity(intent);
                }else {
                    Intent intentAD=new Intent(context, PlaylistSongActivity.class);
                    intentAD.putExtra("idSongAD",advertisementArrayList.get(position));
                    context.startActivity(intentAD);
                }



            }
        });
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
