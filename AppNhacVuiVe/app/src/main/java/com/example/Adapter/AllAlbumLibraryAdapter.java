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

import com.example.Model.Album;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllAlbumLibraryActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumLibraryAdapter extends BaseAdapter {
    AllAlbumLibraryActivity context;
    ArrayList<Album> albumArrayList;

    public AllAlbumLibraryAdapter(AllAlbumLibraryActivity context, ArrayList<Album> albumArrayList) {
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
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item_all_album_library, null);

            viewHolder.imgAlbum = view.findViewById(R.id.imgAlbumLibrary);

            viewHolder.LikeAlbum = view.findViewById(R.id.LikeAlbumLibrary);
            viewHolder.imgMenuAlbumLibrary = view.findViewById(R.id.imgMenuAlbumLibrary);
            viewHolder.txtNameAlum = view.findViewById(R.id.txtNameAlbumLibrary);
            viewHolder.txtSingerAlbum=view.findViewById(R.id.txtSingerAlbumLibrary);


            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Album album=albumArrayList.get(i);
        Picasso.get().load(album.getImgAlbum())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp).into(viewHolder.imgAlbum);

        viewHolder.txtSingerAlbum.setText(album.getSingerAlbum().trim());
        viewHolder.txtNameAlum.setText(album.getNameAlbum().trim());



        viewHolder.LikeAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.albumArrayList.remove(album);
                if(context.albumArrayList.size()==0){
                    context.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
                context.allAlbumLibraryAdapter.notifyDataSetChanged();
                context.getSupportActionBar().setTitle("Album ("+context.albumArrayList.size()+") ");
                MainActivity.database.SetData("DELETE FROM AlbumLike WHERE IdAlbum='"+album.getIdAlbum()+"' ");
                Toast t=Toast.makeText(context,"Đã gỡ "+album.getNameAlbum()+" khỏi thư viện ",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeAlbum(album.getIdAlbum(),-1);
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




        viewHolder.imgMenuAlbumLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t=Toast.makeText(context,"Đã click vào menu của album",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
            }
        });
        return view;
    }

    public class ViewHolder {
        ImageView imgAlbum, LikeAlbum, imgMenuAlbumLibrary;
        TextView txtNameAlum,txtSingerAlbum;
    }
}
