package com.ins.myapplication4.app.fragments.discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ins.myapplication4.app.R;
import com.ins.myapplication4.app.TestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverRecommendFragment extends Fragment implements View.OnClickListener {


    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_recommend, container, false);


        View btn = ret.findViewById(R.id.btnTest);
        btn.setOnClickListener(this);
        return ret;
    }



    @Override
    public void onClick(View v) {
        FragmentActivity context = getActivity();
        Intent intent = new Intent(context, TestActivity.class);
        startActivity(intent);
        //对于startactivity而言，新的是进入
        //动画指定id为0代表没有动画
        context.overridePendingTransition(R.anim.anim_slide,0);
    }
}
