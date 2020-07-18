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

import com.example.Model.Playlist;
import com.example.Model.Song;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllPlaylistLibraryActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlaylistLibraryAdapter extends BaseAdapter {
    AllPlaylistLibraryActivity context;
    ArrayList<Playlist> playlistArrayList;

    public AllPlaylistLibraryAdapter(AllPlaylistLibraryActivity context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @Override
    public int getCount() {
        return playlistArrayList.size();
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
            view = inflater.inflate(R.layout.row_item_all_playlist_library, null);

            viewHolder.imgPlaylist = view.findViewById(R.id.imgPlaylistLibrary);

            viewHolder.LikePlaylist = view.findViewById(R.id.LikePlaylistLibrary);
            viewHolder.imgMenuPlaylistLike = view.findViewById(R.id.imgMenuPlaylistLibrary);
            viewHolder.txtNamePlaylist = view.findViewById(R.id.txtNamePlaylistLibrary);


            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final Playlist pl=playlistArrayList.get(i);

        Picasso.get().load(pl.getIconPlayList())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp).into(viewHolder.imgPlaylist);

        viewHolder.txtNamePlaylist.setText(pl.getNamePlayList());



        viewHolder.LikePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.playlistArrayList.remove(pl);
                if(context.playlistArrayList.size()==0){
                   context.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
                context.allPlaylistLibraryAdapter.notifyDataSetChanged();
                context.getSupportActionBar().setTitle("Playlist ("+context.playlistArrayList.size()+") ");
                MainActivity.database.SetData("DELETE FROM PlaylistLike WHERE IdPlaylist='"+pl.getIdPlayList()+"' ");
                Toast t=Toast.makeText(context,"Đã gỡ "+pl.getNamePlayList()+" khỏi thư viện ",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikePlaylist(pl.getIdPlayList(),-1);
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




        viewHolder.imgMenuPlaylistLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast t=Toast.makeText(context,"Đã click vào menu của playlist",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
            }
        });

        return view;
    }

    public class ViewHolder {
        ImageView imgPlaylist, LikePlaylist, imgMenuPlaylistLike;
        TextView txtNamePlaylist;
    }
}
