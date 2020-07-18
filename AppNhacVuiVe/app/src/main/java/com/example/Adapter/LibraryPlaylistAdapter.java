package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Playlist;
import com.example.Model.Song;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibraryPlaylistAdapter extends RecyclerView.Adapter<LibraryPlaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public LibraryPlaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.row_item_library_song,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(playlistArrayList.get(position).getIconPlayList())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imgLibraryPlaylist);
    }

    @Override
    public int getItemCount() {
        if(playlistArrayList.size()>3){
        return 3;
        }else {
        return playlistArrayList.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLibraryPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLibraryPlaylist=itemView.findViewById(R.id.imgLibrarySong);
        }
    }
}


