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
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllPlaylistAdapter extends RecyclerView.Adapter<AllPlaylistAdapter.ViewHolder> {
    Context context;
    int layout;
    ArrayList<Playlist> playlistArrayList;

    public AllPlaylistAdapter(Context context, int layout, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.layout = layout;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNameAllPlaylist.setText(playlistArrayList.get(position).getNamePlayList().trim());
        Picasso.get().load(playlistArrayList.get(position).getIconPlayList().trim())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imgAllPlaylist);
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAllPlaylist;
        TextView txtNameAllPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllPlaylist=itemView.findViewById(R.id.imgAllPlaylist);
            txtNameAllPlaylist=itemView.findViewById(R.id.txtNameAllPlaylist);
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
