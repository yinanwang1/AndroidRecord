package com.example.arthurwang.helloworld.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.arthurwang.helloworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnotherRightFragment extends Fragment {


    public AnotherRightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_another_right, container, false);
    }

}
