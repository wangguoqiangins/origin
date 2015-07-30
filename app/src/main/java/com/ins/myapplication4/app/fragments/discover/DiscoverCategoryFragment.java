package com.ins.myapplication4.app.fragments.discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ins.myapplication4.app.R;
import com.ins.myapplication4.app.TaskConstants;
import com.ins.myapplication4.app.data.DataStore;
import com.ins.myapplication4.app.model.DiscoverCategory;
import com.ins.myapplication4.app.parsers.DataParser;
import com.ins.myapplication4.app.tasks.TaskCallback;
import com.ins.myapplication4.app.tasks.TaskResult;
import com.ins.myapplication4.app.tasks.impl.DiscoverCategoryTask;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverCategoryFragment extends Fragment implements TaskCallback {


    public DiscoverCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断有没有分类
        List<DiscoverCategory> categories = DataStore.getInstance().getDiscoverCategories();
        if (categories != null && !categories.isEmpty()){
            //有分类
        }else {
            //无分类
            DiscoverCategoryTask task = new DiscoverCategoryTask(this);
            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_category, container, false);
    }


    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;

            if(taskId == TaskConstants.TASK_DISCOVER_CATEGORIES){
                if(data != null){
                    if(data instanceof JSONObject){
                        List<DiscoverCategory> categories = DataParser.parseDiscoverCategories((JSONObject) data);
                        DataStore.getInstance().setDiscoverCategories(categories);
                        //TODO 利用分类，制作UI界面
                    }
                }
            }


        }
    }
}
