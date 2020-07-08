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

import com.example.Model.Theme;
import com.example.appnhacvuive.AllCategoryOfThemeActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AllThemeAdapter extends RecyclerView.Adapter<AllThemeAdapter.ViewHolder> {

    Context context;
    ArrayList<Theme> themeArrayList;

    public AllThemeAdapter(Context context, ArrayList<Theme> themeArrayList) {
        this.context = context;
        this.themeArrayList = themeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.row_item_all_theme,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theme theme=themeArrayList.get(position);
        Picasso.get().load(theme.getImgTheme()).into(holder.imgAllTheme);
        holder.txtAllTheme.setText(theme.getNameTheme().trim());
    }

    @Override
    public int getItemCount() {
        return themeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAllTheme;
        TextView txtAllTheme;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllTheme =itemView.findViewById(R.id.imgAllTheme);
            txtAllTheme= itemView.findViewById(R.id.txtAllTheme);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context, AllCategoryOfThemeActivity.class);
                    intent.putExtra("idTheme",themeArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
