package com.ins.myapplication4.app;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost host = (TabHost) findViewById(R.id.main_tab_host);
        host.setup();
        TabHost.TabSpec tab1 = host.newTabSpec("tab1");

        tab1.setContent(R.layout.activity_splash);
        tab1.setIndicator("扉页");
        host.addTab(tab1);
    }

}
