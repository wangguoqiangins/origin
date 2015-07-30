package com.ins.myapplication4.app.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ins.myapplication4.app.R;
import com.ins.myapplication4.app.TaskConstants;
import com.ins.myapplication4.app.adapters.CommonFragmentPagerAdapter;
import com.ins.myapplication4.app.fragments.discover.*;
import com.ins.myapplication4.app.model.DiscoverTab;
import com.ins.myapplication4.app.parsers.DataParser;
import com.ins.myapplication4.app.tasks.TaskCallback;
import com.ins.myapplication4.app.tasks.TaskResult;
import com.ins.myapplication4.app.tasks.impl.DiscoverTabTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener, TaskCallback{


    /**
     * 放子栏目的
     */
    private ViewPager pager;
    /**
     * 子栏目的tabbar
     */
    private TabLayout tabBar;
    /**
     * tab信息
     */
    private List<DiscoverTab> tabTitles;

    private List<Fragment> subFragments;
    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabTitles = new LinkedList<DiscoverTab>();
        subFragments = new LinkedList<Fragment>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover, container, false);
        View btnSearch = ret.findViewById(R.id.discover_title_search);
        if(btnSearch!=null){

        btnSearch.setOnClickListener(this);
        }
        //TODO 因为Tab的设置是从网路上来的，需要动态的添加
        tabBar = (TabLayout) ret.findViewById(R.id.discover_tab_bar);

//        //tablayout创建一个tab实例
//        TabLayout.Tab tab = tabBar.newTab();
//        tab.setText("推荐");
//        tabBar.addTab(tab);
//
//        TabLayout.Tab tab1 = tabBar.newTab();
//        tab1.setText("分类");
//        tabBar.addTab(tab1);
//
//        TabLayout.Tab tab2 = tabBar.newTab();
//        tab2.setText("直播");
//        tabBar.addTab(tab2);
//
//        TabLayout.Tab tab3 = tabBar.newTab();
//        tab3.setText("榜单");
//        tabBar.addTab(tab3);
//
//        TabLayout.Tab tab4 = tabBar.newTab();
//        tab4.setText("主播");
//        tabBar.addTab(tab4);
//
//        tabBar.setOnTabSelectedListener(this);
        ///////////////////////////////////////////////////////////////
        pager = (ViewPager) ret.findViewById(R.id.discover_pager);
        //设置adapter
        //TODO 由于tab是动态设置的，所以viewpager也需要动态设置

        //viewpager在滑动页面的时候，添加一个监听器
        //监听由TabLayoutOnPageChangeListener来完成
        //从而实现viewpager滚动，上面的tablayout跟随滚动tab
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabBar));
        DiscoverTabTask task = new DiscoverTabTask(this);
        task.execute();

        //加载tabs
        return ret;
    }

    ///////////////////////////////////////////////////////////////////
    //tablayout 的tab选中的接口，使用规则和actionbar是一样的

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        pager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //TODO 进行刷新
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == TaskConstants.TASK_DISCOVER_TAGS) {
                if(data!=null){
                    //TODO 解析JSON
                    if(data instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) data;
                        //下面的这个错误！！！
                        tabTitles = DataParser.parseDiscoverTabs(jsonObject);
                        udateTabs();
                    }

                }else {
                    //设置默认数据
                }
            }
        }
    }

    private void udateTabs(){
        if(tabTitles != null){

            for(DiscoverTab tabTite : tabTitles){
                TabLayout.Tab tab = tabBar.newTab();
                tab.setText(tabTite.getTitle());
                tabBar.addTab(tab);
                //根据内容类型，来设置指定的fragment
                String type = tabTite.getContentType();
                if("recommentd".equals(type)){
                    subFragments.add(new DiscoverRecommendFragment());
                }else if("category".equals(type)){
                    subFragments.add(new DiscoverCategoryFragment());
                }else if("live".equals(type)){
                    subFragments.add(new DiscoverLiveFragment());
                }else if("ranking".equals(type)){
                    subFragments.add(new DiscoverRankingFragment());
                }else if("anchor".equals(type)){
                    subFragments.add(new DiscoverAnchorFragment());
                }
            }
            CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(
                    getChildFragmentManager(),subFragments
            );
            pager.setAdapter(adapter);
        }
    }
}
