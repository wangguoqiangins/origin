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
public class DiscoverTabTask extends BaseTask{

    public DiscoverTabTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId = TaskConstants.TASK_DISCOVER_TAGS;
        String str = ClientDiscoverAPI.getDiscoverTabs();
        if (str != null) {
            try {
                //返回JSON是为了让接收数据的接口实现
                //来检查数据的情况，不直接返回实体
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
