package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Playlist;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> playlistArrayList;

    public PlaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.row_item_playlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(playlistArrayList.get(position).getIconPlayList()).into(holder.imgPlaylist);
        holder.txtNamePlaylist.setText(playlistArrayList.get(position).getNamePlayList());
    }


    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPlaylist,btnPlaylist;
        TextView txtNamePlaylist;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgPlaylist=itemView.findViewById(R.id.imgPlaylist);
            btnPlaylist=itemView.findViewById(R.id.btnPlaylist);
            txtNamePlaylist=itemView.findViewById(R.id.txtNamePlaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlaylistSongActivity.class);
                    intent.putExtra("idPlaylist",playlistArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
