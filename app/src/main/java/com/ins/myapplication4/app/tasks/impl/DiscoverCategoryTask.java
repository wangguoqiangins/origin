package com.ins.myapplication4.app.tasks.impl;

import com.ins.myapplication4.app.TaskConstants;
import com.ins.myapplication4.app.client.ClientDiscoverAPI;
import com.ins.myapplication4.app.tasks.BaseTask;
import com.ins.myapplication4.app.tasks.TaskCallback;
import com.ins.myapplication4.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-7-29.
 */

/**
 * 发现部分分类获取任务
 */
public class DiscoverCategoryTask extends BaseTask{
    public DiscoverCategoryTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId = TaskConstants.TASK_DISCOVER_CATEGORIES;
        //调API：http://mobile.ximalaya.com/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2
        String string = ClientDiscoverAPI.getDiscoverCateries();
        try {
            ret.data = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
