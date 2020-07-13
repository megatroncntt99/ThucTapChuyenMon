package com.example.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.NetworkCheck.ConnectionReceiver;
import com.example.appnhacvuive.R;

public class FragmentHome extends Fragment  {
    View viewHome;
    TextView txtNetworkFail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHome=inflater.inflate(R.layout.fragment_home,container,false);

        remap();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkNetwork();
            }
        },2000);
        return viewHome;
    }

    private void checkNetwork() {
        if(ConnectionReceiver.isConnected()){
            txtNetworkFail.setVisibility(View.GONE);
        }
        else {
            txtNetworkFail.setVisibility(View.VISIBLE);
        }
    }

    private void remap() {
        txtNetworkFail=viewHome.findViewById(R.id.txtNetworkFail);

    }

}
