package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Model.Category;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibraryCategoryAdapter extends RecyclerView.Adapter<LibraryCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Category> categoryArrayList;

    public LibraryCategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_item_library_song, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(categoryArrayList.get(position).getImgCategory())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imgCategory);
    }

    @Override
    public int getItemCount() {
        if (categoryArrayList.size() > 3) {
            return 3;
        } else
            return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgLibrarySong);
        }
    }
}
