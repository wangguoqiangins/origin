package com.ins.myapplication4.app.fragments.discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ins.myapplication4.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverLiveFragment extends Fragment {


    public DiscoverLiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_live, container, false);
    }


}
