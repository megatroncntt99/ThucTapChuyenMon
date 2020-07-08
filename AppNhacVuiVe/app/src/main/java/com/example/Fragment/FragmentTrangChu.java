package com.example.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.NetworkCheck.ConnectionReceiver;
import com.example.appnhacvuive.R;

public class FragmentTrangChu extends Fragment {
    View viewTrangChu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewTrangChu=inflater.inflate(R.layout.fragment_trang_chu,container,false);
        remap();
        adEvent();
        Log.d("AAA", "onCreateView: "+"reload");
        return viewTrangChu;
    }



    private void remap() {
    }
    private void adEvent() {


    }

}
