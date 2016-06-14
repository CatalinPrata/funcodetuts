package com.example.tabstutorial;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment1 extends Fragment {
    private TabsListener tabsListener;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */
    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TabsListener) {
            tabsListener = (TabsListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        Button addTab = (Button) view.findViewById(R.id.button_add_tab);
        Button removeTab = (Button) view.findViewById(R.id.button_remove_tab);

        addTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabsListener.onTabAdded();
            }
        });

        removeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabsListener.onTabRemoved();
            }
        });

        return view;
    }
}