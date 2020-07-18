package com.example.appnhacvuive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.LibraryCategoryAdapter;
import com.example.Adapter.LibraryPlaylistAdapter;
import com.example.Adapter.LibrarySongAdapter;
import com.example.Database.Database;
import com.example.Model.Album;
import com.example.Model.Category;
import com.example.Model.MVMusic;
import com.example.Model.Playlist;
import com.example.Model.Song;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    TextView txtNameFB, txtEmail;
    CircleImageView imgFB;
    LinearLayout llLogin;
    Toolbar toolbarLogin;
    CallbackManager callbackManager;
    LinearLayout llLibrary, llSeeMore;
    TextView txtSeeMoreLibrary;
    RelativeLayout relativeLayoutSong, relativeLayoutPlaylist, relativeLayoutCategory,relativeLayoutAlbum, relativeLayoutMV;

    RecyclerView recyclerViewLibrarySong,recyclerViewLibraryPlaylist,recyclerViewLibraryCategory;
    TextView txtLibraryNumberSong,txtLibraryNumberPlaylist,txtLibraryNumberCategory,txtLibraryNumberAlbum,txtLibraryNumberMV;
    ArrayList<Song> songArrayList;
    ArrayList<Playlist> playlistArrayList;
    ArrayList<Category> categoryArrayList;
    ArrayList<Album> albumArrayList;
    ArrayList<MVMusic> mvMusicArrayList;
    LibrarySongAdapter librarySongAdapter;
    LibraryPlaylistAdapter libraryPlaylistAdapter;
    LibraryCategoryAdapter libraryCategoryAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        remap();
        checkLoginStatus();
        init();
        addEvent();


    }

    @Override
    protected void onResume() {
        loadDataLibrary();
        super.onResume();
    }

    private void loadDataLibrary() {
        //Song
        songArrayList.clear();
       Cursor cursor= MainActivity.database.GetData("SELECT * FROM SongLike");
       while (cursor.moveToNext()){
           songArrayList.add(new Song(
                   cursor.getString(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getString(3),
                   cursor.getString(4),
                   cursor.getString(5)));
       }
        Collections.reverse(songArrayList);
       librarySongAdapter=new LibrarySongAdapter(LoginActivity.this,songArrayList);
       recyclerViewLibrarySong.setAdapter(librarySongAdapter);

       txtLibraryNumberSong.setText(songArrayList.size()+"");

       //Playlist
        playlistArrayList.clear();
        Cursor cursor1= MainActivity.database.GetData("SELECT * FROM PlaylistLike");
        while (cursor1.moveToNext()){
            playlistArrayList.add(new Playlist(
                    cursor1.getString(0),
                    cursor1.getString(1),
                    cursor1.getString(2),
                    cursor1.getString(3)));
        }
        Collections.reverse(playlistArrayList);
        libraryPlaylistAdapter=new LibraryPlaylistAdapter(LoginActivity.this,playlistArrayList);
        recyclerViewLibraryPlaylist.setAdapter(libraryPlaylistAdapter);

        txtLibraryNumberPlaylist.setText(playlistArrayList.size()+"");

        //Category
        categoryArrayList.clear();
        Cursor cursor2=MainActivity.database.GetData("SELECT * FROM CategoryLike");

        while (cursor2.moveToNext()){
            categoryArrayList.add(new Category(cursor2.getString(0),
                    cursor2.getString(1),
                    cursor2.getString(2),
                    cursor2.getString(3)));
        }
        Collections.reverse(categoryArrayList);
        libraryCategoryAdapter=new LibraryCategoryAdapter(LoginActivity.this,categoryArrayList);
        recyclerViewLibraryCategory.setAdapter(libraryCategoryAdapter);

        txtLibraryNumberCategory.setText(categoryArrayList.size()+"");

        //Album
        albumArrayList.clear();
        Cursor cursor3=MainActivity.database.GetData("SELECT * FROM AlbumLike");

        while (cursor3.moveToNext()){
            albumArrayList.add(new Album(cursor3.getString(0),
                    cursor3.getString(1),
                    cursor3.getString(2),
                    cursor3.getString(3)));
        }
        Collections.reverse(albumArrayList);
        txtLibraryNumberAlbum.setText(albumArrayList.size()+"");

        //MV
        mvMusicArrayList.clear();
        Cursor cursor4=MainActivity.database.GetData("SELECT * FROM MVLike");

        while (cursor4.moveToNext()){
            mvMusicArrayList.add(new MVMusic(
                    cursor4.getString(0),
                    cursor4.getString(1),
                    cursor4.getString(2),
                    cursor4.getString(3),
                    cursor4.getString(4),
                    cursor4.getString(5),
                    cursor4.getString(6),
                    cursor4.getString(7)));
        }
        Collections.reverse(mvMusicArrayList);
        txtLibraryNumberMV.setText(mvMusicArrayList.size()+"");


    }

    private void remap() {
        songArrayList=new ArrayList<>();
        playlistArrayList=new ArrayList<>();
        categoryArrayList=new ArrayList<>();
        albumArrayList=new ArrayList<>();
        mvMusicArrayList=new ArrayList<>();
        loginButton = findViewById(R.id.login_button);
        txtNameFB = findViewById(R.id.txtNameFB);
        imgFB = findViewById(R.id.imgFB);
        llLogin = findViewById(R.id.llLogin);
        toolbarLogin = findViewById(R.id.toolbarLogin);
        txtEmail = findViewById(R.id.txtEmail);

        llLibrary = findViewById(R.id.llLibrary);
        llSeeMore = findViewById(R.id.llSeeMore);
        txtSeeMoreLibrary = findViewById(R.id.txtSeeMoreLibrary);
        relativeLayoutSong = findViewById(R.id.relativeLayoutSong);
        relativeLayoutPlaylist = findViewById(R.id.relativeLayoutPlaylist);
        relativeLayoutCategory=findViewById(R.id.relativeLayoutCategory);
        relativeLayoutAlbum = findViewById(R.id.relativeLayoutAlbum);
        relativeLayoutMV = findViewById(R.id.relativeLayoutMV);


        llSeeMore.setTag(1);
        LinearLayoutManager layoutManager=new LinearLayoutManager(LoginActivity.this, RecyclerView.HORIZONTAL,false);
        //Song
        recyclerViewLibrarySong=findViewById(R.id.recyclerViewLibrarySong);
        recyclerViewLibrarySong.setLayoutManager(layoutManager);
        txtLibraryNumberSong=findViewById(R.id.txtLibraryNumberSong);
        //Playlist
        recyclerViewLibraryPlaylist=findViewById(R.id.recyclerViewLibraryPlaylist);
        recyclerViewLibraryPlaylist.setLayoutManager( new LinearLayoutManager(LoginActivity.this, RecyclerView.HORIZONTAL,false));
        txtLibraryNumberPlaylist=findViewById(R.id.txtLibraryNumberPlaylist);
        //Category
        recyclerViewLibraryCategory=findViewById(R.id.recyclerViewLibraryCategory);
        recyclerViewLibraryCategory.setLayoutManager(new LinearLayoutManager(LoginActivity.this,RecyclerView.HORIZONTAL,false));
        txtLibraryNumberCategory=findViewById(R.id.txtLibraryNumberCategory);

        //Album
        txtLibraryNumberAlbum=findViewById(R.id.txtLibraryNumberAlbum);

        //MV
        txtLibraryNumberMV=findViewById(R.id.txtLibraryNumberMV);
    }

    private void init() {
        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tài khoản cá nhân");
        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        finish();
    }

    private void addEvent() {
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        llSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((int) llSeeMore.getTag() == 1) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llLibrary.getLayoutParams();
                    params.height = (int) convertDpToPixels(376, LoginActivity.this);
                    llLibrary.setLayoutParams(params);
                    llSeeMore.setTag(2);
                    txtSeeMoreLibrary.setText("Thu gọn");
                    txtSeeMoreLibrary.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);

                }
                else if ((int) llSeeMore.getTag() == 2) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llLibrary.getLayoutParams();
                    params.height = (int) convertDpToPixels(150, LoginActivity.this);
                    llLibrary.setLayoutParams(params);
                    llSeeMore.setTag(1);
                    txtSeeMoreLibrary.setText("Xem thêm");
                    txtSeeMoreLibrary.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
                }
            }
        });

        relativeLayoutSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(LoginActivity.this,AllSongLibraryActivity.class);
//                intent.putExtra("songLibrary",songArrayList);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        relativeLayoutPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(LoginActivity.this,AllPlaylistLibraryActivity.class);
                intent.putExtra("playlistLibrary",playlistArrayList);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        relativeLayoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(LoginActivity.this,AllCategoryLibraryActivity.class);
                intent.putExtra("categoryLibrary",categoryArrayList);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
        relativeLayoutAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(LoginActivity.this,AllAlbumLibraryActivity.class);
                intent.putExtra("albumLibrary",albumArrayList);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });
        relativeLayoutMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(LoginActivity.this,AllMVLibraryActivity.class);
                intent.putExtra("mvLibrary",mvMusicArrayList);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken == null) {
                txtNameFB.setText("Megatron");
                txtEmail.setText("@gmail.com");
                imgFB.setImageResource(R.mipmap.ic_launcher);
                llLogin.setVisibility(View.GONE);
                MainActivity.database.SetData("DELETE FROM Account");
                Toast.makeText(LoginActivity.this, "Đăng xuất", Toast.LENGTH_LONG).show();
            } else {
                llLogin.setVisibility(View.VISIBLE);
                loadUser(currentAccessToken);
            }
        }
    };

    private void loadUser(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    llLogin.setVisibility(View.VISIBLE);
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String img_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                    txtNameFB.setText(first_name + " " + last_name);
                    txtEmail.setText(email);
                    Picasso.get().load(img_url)
                            .placeholder(R.drawable.custom_load_image)
                            .error(R.drawable.ic_error_outline_black_24dp)
                            .into(imgFB);
                    String sql = "select * from Account";
                    Cursor cursor = MainActivity.database.GetData(sql);
                    if (cursor.getCount() == 0) {
                        MainActivity.database.SetData("Insert into Account values('" + img_url + "','" + first_name + "','" + last_name + "','" + email + "')");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "first_name,last_name,email,id");
        request.setParameters(bundle);
        request.executeAsync();
    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            String sql = "select * from Account";
            Cursor cursor = MainActivity.database.GetData(sql);
            while (cursor.moveToNext()) {
                Picasso.get().load(cursor.getString(0))
                        .placeholder(R.drawable.custom_load_image)
                        .error(R.drawable.ic_error_outline_black_24dp)
                        .into(imgFB);
                txtNameFB.setText(cursor.getString(1) + " " + cursor.getString(2));
                txtEmail.setText(cursor.getString(3));
            }
        } else {
            llLogin.setVisibility(View.GONE);
        }
    }

    public static float convertDpToPixels(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}

