package com.ins.myapplication4.app.tasks.impl;

import com.ins.myapplication4.app.TaskConstants;
import com.ins.myapplication4.app.client.ClientDiscoverAPI;
import com.ins.myapplication4.app.tasks.BaseTask;
import com.ins.myapplication4.app.tasks.TaskCallback;
import com.ins.myapplication4.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-7-28.
 */
public class CategoryTagMenuTaks extends BaseTask {
    public CategoryTagMenuTaks(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {

        // TaskResult 必须创建，用来描述任务类型以及数据

        TaskResult ret = new TaskResult();

        ret.taskId = TaskConstants.TASK_CATEGORY_TAG_MENU;

        /////////////////////////////////////////

        String type = null;

        if (params != null && params.length > 0){
            type = params[0];
        }

        String str = ClientDiscoverAPI.getCategoryTagMenu(type);

        if (str != null){

            try {
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
