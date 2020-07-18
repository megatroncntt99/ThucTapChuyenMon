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

import com.example.Model.Category;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllCategoryLibraryActivity;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoryLibraryAdapter extends BaseAdapter {
    AllCategoryLibraryActivity context;
    ArrayList<Category> categoryArrayList;

    public AllCategoryLibraryAdapter(AllCategoryLibraryActivity context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
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
            view = inflater.inflate(R.layout.row_item_all_category_library, null);

            viewHolder.imgCategory = view.findViewById(R.id.imgCategoryLibrary);

            viewHolder.LikeCategory = view.findViewById(R.id.LikeCategoryLibrary);
            viewHolder.imgMenuCategoryLibrary = view.findViewById(R.id.imgMenuCategoryLibrary);
            viewHolder.txtNameCategory = view.findViewById(R.id.txtNameCategoryLibrary);


            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }

        final Category category=categoryArrayList.get(i);

        Picasso.get().load(category.getImgCategory())
                .placeholder(R.drawable.custom_load_image)
                .error(R.drawable.ic_error_outline_black_24dp).into(viewHolder.imgCategory);

        viewHolder.txtNameCategory.setText(category.getNameCategory());



        viewHolder.LikeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.categoryArrayList.remove(category);
                if(context.categoryArrayList.size()==0){
                    context.linearLayoutNoData.setVisibility(View.VISIBLE);
                }
                context.allCategoryLibraryAdapter.notifyDataSetChanged();
                context.getSupportActionBar().setTitle("Thể loại ("+context.categoryArrayList.size()+") ");
                MainActivity.database.SetData("DELETE FROM CategoryLike WHERE IdCategory='"+category.getIdCategory()+"' ");
                Toast t=Toast.makeText(context,"Đã gỡ "+category.getNameCategory()+" khỏi thư viện ",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

                DataService dataService= APIService.getService();
                Call<String> callBack=dataService.SetUpdateLikeCategory(category.getIdCategory(),-1);
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




        viewHolder.imgMenuCategoryLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast t=Toast.makeText(context,"Đã click vào menu của thể loại",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
            }
        });
        return view;
    }
    public class ViewHolder {

        ImageView imgCategory, LikeCategory, imgMenuCategoryLibrary;
        TextView txtNameCategory;
    }
}
