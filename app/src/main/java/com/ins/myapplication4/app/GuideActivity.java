package com.ins.myapplication4.app;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.view.WindowCallbackWrapper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.ins.myapplication4.app.adapters.GuideAdapter;
import com.ins.myapplication4.app.tasks.TaskCallback;
import com.ins.myapplication4.app.tasks.TaskResult;
import com.ins.myapplication4.app.tasks.impl.CategoryTagMenuTaks;
import com.ins.myapplication4.app.util.PackageUtil;

import java.util.ArrayList;

/**
 * 教程页
 */
public class GuideActivity extends FragmentActivity implements View.OnClickListener {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        ///////////////////////////////////////////////////////////////
        //设置sharedpreferences，只要教程出来，就设置
        SharedPreferences sp = getSharedPreferences(TaskConstants.SP_NAME, MODE_PRIVATE);
        String versionName = PackageUtil.getPackageVersionName(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TaskConstants.SP_KEY_GUIDE_FIRST_RUN, versionName);
        editor.commit();

        ArrayList<Integer> images = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            images.add(R.mipmap.ic_launcher);}
        ViewPager pager = (ViewPager) findViewById(R.id.guide_view_pager);
        GuideAdapter adapter = new GuideAdapter(images, this);
        adapter.setGoOnClickListener(this);
        pager.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
