package com.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appnhacvuive.R;

public class FragmentTimKiem extends Fragment {
    View viewTimKiem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewTimKiem=inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        return viewTimKiem;
    }
}
