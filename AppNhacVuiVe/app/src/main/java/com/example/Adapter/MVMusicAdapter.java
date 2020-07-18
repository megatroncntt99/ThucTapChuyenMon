package com.example.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
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

import androidx.cardview.widget.CardView;

import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.MVMusic;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MVMusicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MVMusic> mvMusicArrayList;

    public MVMusicAdapter(Context context, ArrayList<MVMusic> mvMusicArrayList) {
        this.context = context;
        this.mvMusicArrayList = mvMusicArrayList;
    }
    public void addMVMusic(ArrayList<MVMusic> allItem){
        mvMusicArrayList.addAll(allItem);
        this.notifyDataSetChanged();
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
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.row_item_mv,null);

            viewHolder.imgMV=view.findViewById(R.id.imgMV);
            viewHolder.imgSinger=view.findViewById(R.id.imgSinger);
            viewHolder.imgLike=view.findViewById(R.id.imgLikeMV);
            viewHolder.imgUnLike=view.findViewById(R.id.imgDislikeMV);

            viewHolder.txtNameSong=view.findViewById(R.id.txtNameSongMV);
            viewHolder.txtSinger=view.findViewById(R.id.txtNameSingerMV);
            viewHolder.txtTimeMV=view.findViewById(R.id.txtTimeMV);
            viewHolder.txtNumberLikeMV=view.findViewById(R.id.txtNumberLikeMV);
            viewHolder.cardView=view.findViewById(R.id.CardViewMV);
            viewHolder.cardView.setRadius(30);

            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        final MVMusic MV=mvMusicArrayList.get(i);
        Picasso.get().load(MV.getImgSongMV()).placeholder(R.drawable.custom_load_image).error(R.drawable.ic_error_outline_black_24dp).into(viewHolder.imgMV);
        Picasso.get().load(MV.getImgSinger()).into(viewHolder.imgSinger);

        String sql="select * from MVLike";
        boolean like=false;
        Cursor cursor= MainActivity.database.GetData(sql);
        while (cursor.moveToNext()){
            if(MV.getIdMV().trim().equals(cursor.getString(0))){
                like=true;
                break;
            }
        }
        if(like){
            viewHolder.imgLike.setVisibility(View.VISIBLE);
            viewHolder.imgUnLike.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.imgLike.setVisibility(View.INVISIBLE);
            viewHolder.imgUnLike.setVisibility(View.VISIBLE);
        }
        viewHolder.txtNameSong.setText(MV.getNameSong().trim());
        viewHolder.txtSinger.setText(MV.getNameSinger().trim());
        viewHolder.txtTimeMV.setText(MV.getTxtTimeMV().trim());
        final long[] numberLike = {Long.valueOf(MV.getLikeMVMusic())};
        viewHolder.txtNumberLikeMV.setText(new FunctionDesign().format(numberLike[0]));


        viewHolder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeMVMusic(MV.getIdMV(),-1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){
                            viewHolder.imgLike.setVisibility(View.INVISIBLE);
                            viewHolder.imgUnLike.setVisibility(View.VISIBLE);
                            numberLike[0]--;
                            viewHolder.txtNumberLikeMV.setText(new FunctionDesign().format(numberLike[0]));
                            MainActivity.database.SetData("DELETE FROM MVLike WHERE IdMV='"+MV.getIdMV()+"' ");

                            Toast t=Toast.makeText(context, "Đã gỡ "+MV.getNameSong()+" khỏi thư viện ", Toast.LENGTH_SHORT);
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
        viewHolder.imgUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeMVMusic(MV.getIdMV(),1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result=response.body();
                        if(result.equals("success")){
                            viewHolder.imgLike.setVisibility(View.VISIBLE);
                            viewHolder.imgUnLike.setVisibility(View.INVISIBLE);
                            numberLike[0]++;
                            MainActivity.database.InsertDataMVLike("MVLike",MV.getIdMV(),MV.getKeyMV(),MV.getImgSongMV(),MV.getImgSinger(),MV.getTxtTimeMV(),MV.getNameSong(),MV.getNameSinger(),MV.getLikeMVMusic());
                            viewHolder.txtNumberLikeMV.setText(new FunctionDesign().format(numberLike[0]));
                            Toast t=Toast.makeText(context,"Đã thêm "+MV.getNameSong()+" vào thư viện ",Toast.LENGTH_SHORT);
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

        return view;
    }
    public class ViewHolder{
        ImageView imgMV,imgSinger,imgLike,imgUnLike;
        TextView txtNameSong,txtSinger,txtTimeMV,txtNumberLikeMV;
        CardView cardView;
    }

}
