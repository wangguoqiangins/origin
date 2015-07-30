package com.ins.myapplication4.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-7-29.
 */
public class DiscoverTab {
    /**
     * 显示在discoverfragment上面的tab标题
     */
    private String title;
    /**
     * 内容的描述，代码判断的时候用的
     */
    private String contentType;

    public void parseJSON(JSONObject json) throws JSONException {
        if(json != null){
            title = json.getString("title");
            contentType = json.getString("contentType");
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContentType() {
        return contentType;
    }
}
