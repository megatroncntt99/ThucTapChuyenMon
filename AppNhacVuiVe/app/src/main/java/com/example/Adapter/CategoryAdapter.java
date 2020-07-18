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

import com.example.Model.Category;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    Activity context;
    ArrayList<Category> categoryArrayList;

    public CategoryAdapter(Activity context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.row_item_category,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.get().load(categoryArrayList.get(position).getImgCategory())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imgCategory);
        holder.txtNameCate.setText(categoryArrayList.get(position).getNameCategory().trim());
        holder.btnCategory.setOnClickListener(new View.OnClickListener() {
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
                        Call<ArrayList<Song>> callBack = dataService.GetDataSongCategory(categoryArrayList.get(position).getIdCategory());
                        callBack.enqueue(new Callback<ArrayList<Song>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Song>> call, Response<ArrayList<Song>> response) {


                                ArrayList<Song>songArrayList = response.body();
                                dialog.dismiss();
                                if(songArrayList.size()>0){
                                    MainActivity.songArrayList.clear();
                                    Intent intent=new Intent(context, PlaySongActivity.class);
                                    intent.putExtra("arraySong",songArrayList);
                                    intent.putExtra("nameList",categoryArrayList.get(position).getNameCategory());

                                    context.startActivity(intent);
                                    context.overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                                }
                                else {
                                    Toast.makeText(context, "Chưa có bài hát hết", Toast.LENGTH_SHORT).show();
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
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCategory;
        TextView txtNameCate;
        RelativeLayout btnCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCategory=itemView.findViewById(R.id.btnCategory);
            imgCategory=itemView.findViewById(R.id.imgCategory);
            txtNameCate=itemView.findViewById(R.id.txtNameCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlaylistSongActivity.class);
                    intent.putExtra("idCategory",categoryArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
