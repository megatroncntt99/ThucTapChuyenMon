package com.example.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.Model.Category;
import com.example.Model.Theme;
import com.example.Model.ThemeCategory;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.AllCategoryOfThemeActivity;
import com.example.appnhacvuive.AllTheme_CategoryActivity;
import com.example.appnhacvuive.PlaylistSongActivity;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Theme_Category extends Fragment {
    public static final String TAG="AAA";
    HorizontalScrollView horizontalScrollView;
    RelativeLayout rlTheme_Category;
    View viewThemeCategory;
    TextView txtSeeMoreTheme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewThemeCategory=inflater.inflate(R.layout.fragment_theme_category,null);
        remap();
        GetData();
        addEvent();
        return viewThemeCategory;
    }



    private void remap() {
        horizontalScrollView= viewThemeCategory.findViewById(R.id.HorizontalScrollViewThemCategory);
        rlTheme_Category=viewThemeCategory.findViewById(R.id.rlTheme_Category);
        txtSeeMoreTheme=viewThemeCategory.findViewById(R.id.txtSeeMoreTheme);
    }
    private void GetData() {
        DataService dataService= APIService.getService();
        Call<ThemeCategory> callBack=dataService.GetDataTheme_category();
        callBack.enqueue(new Callback<ThemeCategory>() {
            @Override
            public void onResponse(Call<ThemeCategory> call, Response<ThemeCategory> response) {
                ThemeCategory themeCategory=response.body();
                if(themeCategory!=null){
                    final ArrayList<Theme> themeArrayList= new ArrayList<>();
                    themeArrayList.addAll(themeCategory.getTheme());
                    Collections.shuffle(themeArrayList);

                    final ArrayList<Category> categoryArrayList=new ArrayList<>();
                    categoryArrayList.addAll(themeCategory.getCategory());
                    Collections.shuffle(categoryArrayList);




                    LinearLayout linearLayout=new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(580,300);
                    layoutParams.setMargins(10,15,10,20);

                    for (int i=0;i<themeArrayList.size();i++){
                        CardView cardView=new CardView(getActivity());
                        cardView.setRadius(10);
                        ImageView imageView=new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        TextView txtNameTheme=new TextView(getActivity());

                        txtNameTheme.setTextColor(Color.WHITE);
                        txtNameTheme.setTextSize(20);
                        txtNameTheme.setTypeface(null, Typeface.BOLD);
                        txtNameTheme.setGravity(Gravity.CENTER);

                        if(themeArrayList.get(i).getImgTheme()!=null){
                            Picasso.get().load(themeArrayList.get(i).getImgTheme())
                                    .placeholder(R.drawable.custom_load_image)
                                    .error(R.drawable.ic_error_outline_black_24dp).into(imageView);
                            txtNameTheme.setText(themeArrayList.get(i).getNameTheme().toUpperCase().trim());
                        }
                        cardView.setLayoutParams(layoutParams);

                        cardView.addView(imageView);
                        cardView.addView(txtNameTheme);

                        linearLayout.addView(cardView);
                        final int finalI = i;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent =new Intent(getActivity(), AllCategoryOfThemeActivity.class);
                                intent.putExtra("idTheme",themeArrayList.get(finalI));
                                startActivity(intent);
                            }
                        });


                    }

                    for (int j=0;j<categoryArrayList.size();j++){
                        CardView cardView=new CardView(getActivity());
                        cardView.setRadius(10);
                        ImageView imageView=new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                        if(categoryArrayList.get(j).getImgCategory()!=null){
                            Picasso.get().load(categoryArrayList.get(j).getImgCategory())
                                    .placeholder(R.drawable.custom_load_image)
                                    .error(R.drawable.ic_error_outline_black_24dp)
                                    .into(imageView);

                        }
                        cardView.setLayoutParams(layoutParams);
                        cardView.addView(imageView);


                        linearLayout.addView(cardView);

                        final int finalJ1 = j;
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getActivity(), PlaylistSongActivity.class);
                                intent.putExtra("idCategory",categoryArrayList.get(finalJ1));
                                startActivity(intent);
                            }
                        });
                    }
                    if(linearLayout!=null){
                        try {
                            horizontalScrollView.addView(linearLayout);
                        }catch (Exception e){

                        }
                    }
                    else {

                    }
                }



            }

            @Override
            public void onFailure(Call<ThemeCategory> call, Throwable t) {

            }
        });
    }

    private void addEvent() {
        rlTheme_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllTheme_CategoryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
        txtSeeMoreTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AllTheme_CategoryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
    }


}
