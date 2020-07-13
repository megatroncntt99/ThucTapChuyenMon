package com.example.appnhacvuive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
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

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    TextView txtNameFB, txtEmail;
    CircleImageView imgFB;
    LinearLayout llLogin;
    Toolbar toolbarLogin;
    CallbackManager callbackManager;
    LinearLayout llLibrary,llSeeMore;
    TextView txtSeeMoreLibrary;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        remap();
        init();
        addEvent();
        checkLoginStatus();
    }
    private void remap() {
        loginButton = findViewById(R.id.login_button);
        txtNameFB = findViewById(R.id.txtNameFB);
        imgFB = findViewById(R.id.imgFB);
        llLogin = findViewById(R.id.llLogin);
        toolbarLogin = findViewById(R.id.toolbarLogin);
        txtEmail = findViewById(R.id.txtEmail);

        llLibrary=findViewById(R.id.llLibrary);
        llSeeMore=findViewById(R.id.llSeeMore);
        txtSeeMoreLibrary=findViewById(R.id.txtSeeMoreLibrary);

        llSeeMore.setTag(1);

    }
    private void init() {
        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tài khoản cá nhân");
        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                if((int)llSeeMore.getTag()==1){
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llLibrary.getLayoutParams();
                    params.height = (int) convertDpToPixels(300,LoginActivity.this);
                    llLibrary.setLayoutParams(params);
                    llSeeMore.setTag(2);
                    txtSeeMoreLibrary.setText("Thu gọn");
                    txtSeeMoreLibrary.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);

                }
                else if((int)llSeeMore.getTag()==2){
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llLibrary.getLayoutParams();
                    params.height = (int) convertDpToPixels(150,LoginActivity.this);
                    llLibrary.setLayoutParams(params);
                    llSeeMore.setTag(1);
                    txtSeeMoreLibrary.setText("Xem thêm");
                    txtSeeMoreLibrary.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
                }
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
                            .placeholder(R.drawable.custom_progress_bar)
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
        if(AccessToken.getCurrentAccessToken()!=null){
            loadUser(AccessToken.getCurrentAccessToken());
        }
    }

    public static float convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi/160f);
        return px;
    }
}

