package com.ins.myapplication4.app;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.ins.myapplication4.app.fragments.CustomFragment;
import com.ins.myapplication4.app.fragments.DiscoverFragment;
import com.ins.myapplication4.app.fragments.DownloadTingFragment;
import com.ins.myapplication4.app.fragments.ProfileFragment;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private DiscoverFragment discoverFragment;
    private CustomFragment customFragment;
    private DownloadTingFragment downloadTingFragment;
    private ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup tabBar = (RadioGroup) findViewById(R.id.main_tab_bar);
        tabBar.setOnCheckedChangeListener(this);
        //默认选中第一个
        tabBar.check(R.id.main_tab_item_discover);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        android.support.v4.app.Fragment fragment = null;
        switch (checkedId){
            case R.id.main_tab_item_discover:
                if(discoverFragment == null){
                    discoverFragment = new DiscoverFragment();
                }
                fragment = discoverFragment;
                break;
            case R.id.main_tab_item_custom:
                if(customFragment == null){
                    customFragment = new CustomFragment();
                }
                fragment = customFragment;
                break;
            case R.id.main_tab_item_download:
                if(downloadTingFragment == null){
                    downloadTingFragment = new DownloadTingFragment();
                }
                fragment = downloadTingFragment;
                break;
            case R.id.main_tab_item_profile:
                if(profileFragment == null){
                    profileFragment = new ProfileFragment();
                }
                fragment = profileFragment;
                break;
        }

        transaction.replace(R.id.main_fragment_container,fragment);
        transaction.commit();
    }
}
