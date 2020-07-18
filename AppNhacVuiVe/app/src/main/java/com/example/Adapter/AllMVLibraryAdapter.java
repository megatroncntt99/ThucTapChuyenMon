package com.example.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Model.MVMusic;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllMVLibraryActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMVLibraryAdapter extends BaseAdapter {
    AllMVLibraryActivity context;
    ArrayList<MVMusic> mvMusicArrayList;

    public AllMVLibraryAdapter(AllMVLibraryActivity context, ArrayList<MVMusic> mvMusicArrayList) {
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
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item_all_mv_library, null);

            viewHolder.imgMV = view.findViewById(R.id.imgMVLibrary);

            viewHolder.LikeMV = view.findViewById(R.id.LikeMVLibrary);
            viewHolder.txtNameMV = view.findViewById(R.id.txtNameMVLibrary);
            viewHolder.txtSingerMV = view.findViewById(R.id.txtSingerMVLibrary);
            viewHolder.txtTimeMV=view.findViewById(R.id.txtTimeMVLibrary);


            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final MVMusic mvMusic=mvMusicArrayList.get(i);

        Picasso.get().load(mvMusic.getImgSongMV().trim())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(viewHolder.imgMV);

        viewHolder.txtNameMV.setText(mvMusic.getNameSong().trim());
        viewHolder.txtSingerMV.setText(mvMusic.getNameSinger().trim());
        viewHolder.txtTimeMV.setText(mvMusic.getTxtTimeMV());




        viewHolder.LikeMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.mvMusicArrayList.remove(mvMusic);
                if(context.mvMusicArrayList.size()==0){
                    context.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
                context.allMVLibraryAdapter.notifyDataSetChanged();
                context.getSupportActionBar().setTitle("MV ("+context.mvMusicArrayList.size()+") ");
                MainActivity.database.SetData("DELETE FROM MVLike WHERE IdMV='"+mvMusic.getIdMV()+"' ");
                Toast t=Toast.makeText(context,"Đã gỡ "+mvMusic.getNameSong()+" khỏi thư viện ",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeMVMusic(mvMusic.getIdMV(),-1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast t1=Toast.makeText(context,"Lỗi kết nối mạng",Toast.LENGTH_SHORT);
                        t1.setGravity(Gravity.CENTER,0,0);
                        t1.show();
                    }
                });

            }
        });


        return view;
    }

    public class ViewHolder {
        ImageView imgMV, LikeMV;
        TextView txtNameMV,txtSingerMV,txtTimeMV;
    }
}

