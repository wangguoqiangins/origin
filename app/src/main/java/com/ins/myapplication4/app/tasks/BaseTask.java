package com.ins.myapplication4.app.tasks;

import android.os.AsyncTask;

/**
 * Created by Administrator on 15-7-28.
 */
public abstract class BaseTask extends AsyncTask<String,Integer,TaskResult> {
    private TaskCallback callback;

    public BaseTask(TaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        if (callback != null){
            callback.onTaskFinished(taskResult);
        }
        super.onPostExecute(taskResult);
    }
}
