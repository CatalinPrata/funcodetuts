package com.example.tabstutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
      @return A new instance of fragment Fragment3.
     */
    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }
}