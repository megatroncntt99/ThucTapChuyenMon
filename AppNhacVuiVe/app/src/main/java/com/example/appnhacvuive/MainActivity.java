package com.example.appnhacvuive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.MainViewPagerAdapter;
import com.example.Fragment.FragmentBXH;
import com.example.Fragment.FragmentHome;
import com.example.Fragment.FragmentTimKiem;
import com.example.Fragment.FragmentTrangChu;
import com.example.NetworkCheck.ConnectionReceiver;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView imgBack_or_Account,imgMicro_or_Delete;
    EditText edtSearch;

    public static final int REQUEST_CODE_MICROPHONE=1;
    public static final int REQUEST_CODE_STORAGE=2;
    public static final int REQUEST_CODE_CALL=3;
    public static final int REQUEST_CODE_STORAGE_CALL=4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissionFile_CALL();
        remap();
        event();
        createFragmentHome();
        edtSearch.setCursorVisible(false);
        closeKeyboard();


    }

    private void closeKeyboard() {
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }


    public void requestPermissionAudio() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_CODE_MICROPHONE);

    }

    private void requestPermissionFile_CALL() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE},REQUEST_CODE_STORAGE_CALL);
    }



    public void requestPermissionFile() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},REQUEST_CODE_STORAGE);
    }
    public void requestPermissionCall() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE_CALL);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_MICROPHONE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this, "Bạn đã từ chối quyền Ghi Âm Nên không thể thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }

                break;
            case REQUEST_CODE_STORAGE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this, "Bạn đã từ chối quyền truy cập vào quản lý Nên không thể thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_CALL:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this, "Bạn đã từ chối quyền truy cập vào phone Nên không thể thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void createFragmentSearch() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentTimKiem fragmentTimKiem=new FragmentTimKiem();
        fragmentTransaction.add(R.id.frameLayoutHome_or_Search,fragmentTimKiem,"fragSearch");
        fragmentTransaction.addToBackStack("search");
        fragmentTransaction.commit();
    }

    private void createFragmentHome() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentHome fragmentHome=new FragmentHome();

        fragmentTransaction.add(R.id.frameLayoutHome_or_Search,fragmentHome,"fragHome");
        fragmentTransaction.addToBackStack("home");
        fragmentTransaction.commit();
    }

    private void event() {

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtSearch.getText().toString().trim().equals("")==true){
                    imgMicro_or_Delete.setImageResource(R.drawable.ic_micro);
                    imgMicro_or_Delete.setTag(1);
                }else {
                    imgMicro_or_Delete.setImageResource(R.drawable.ic_cancel);
                    imgMicro_or_Delete.setTag(2);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }



    private void remap() {
        imgBack_or_Account=findViewById(R.id.imgBack_or_Account);
        imgMicro_or_Delete=findViewById(R.id.imgMicro_or_Delete);
        edtSearch=findViewById(R.id.edtSearch);
        imgBack_or_Account.setTag(1);
        imgMicro_or_Delete.setTag(1);

    }

    public void eventClick(View view) {
        switch (view.getId()){
            case R.id.edtSearch:
                createFragmentSearch();
                imgBack_or_Account.setImageResource(R.drawable.ic_back);
                imgBack_or_Account.setTag(2);
                edtSearch.setCursorVisible(true);



                break;
            case R.id.imgBack_or_Account:
                if((int)imgBack_or_Account.getTag()==2){
                    Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack("home",0);
                    imgBack_or_Account.setImageResource(R.drawable.ic_person);
                    imgBack_or_Account.setTag(1);
                    edtSearch.setCursorVisible(false);
                    closeKeyboard();
                    edtSearch.setText("");

                }
                else if((int)imgBack_or_Account.getTag()==1){
                    Toast.makeText(this, "Tài Khoản", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.imgMicro_or_Delete:
                if((int)imgMicro_or_Delete.getTag()==2){
                    edtSearch.setText("");
                }
                else if((int)imgMicro_or_Delete.getTag()==1) {
                    requestPermissionAudio();
                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()==2){
            if((int)imgBack_or_Account.getTag()==2){
                Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().popBackStack("home",0);
                imgBack_or_Account.setImageResource(R.drawable.ic_person);
                imgBack_or_Account.setTag(1);
            }
        }else  finish();

    }

}
