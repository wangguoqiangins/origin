package com.ins.myapplication4.app.model;

/**
 * Created by Administrator on 15-7-29.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 发现的大分类
 *"id": 3,
 "name": "book",
 "title": "有声小说",
 "isChecked": false,
 "orderNum": 1,
 "coverPath": "http://fdfs.xmcdn.com/group8/M07/17/A0/wKgDYFVxM-fQsucFAAAFRHjovdg062.png",
 "selectedSwitch": false,
 "isFinished": true,
 "contentType": "album"
 */
public class DiscoverCategory implements Comparable<DiscoverCategory>{
    private int id;
    private String name;
    private String title;
    private boolean cheched;
    private int orderNum;
    private String coverPath;
    private boolean selectedSwitch;
    private boolean finished;
    private String contentType;


    public void parseJSON(JSONObject json) throws JSONException {
        if(json != null){
            id = json.getInt("id");
            name = json.optString("name");
            title = json.getString("title");
            cheched = json.optBoolean("isChecked");
            orderNum = json.getInt("orderNum");
            coverPath = json.getString("coverPath");
            selectedSwitch = json.optBoolean("selectedSwitch");
            finished = json.optBoolean("isFinished");
            contentType = json.getString("contentType");
        }
    }


    @Override
    public int compareTo(DiscoverCategory another) {
        int ret = 0;
        if(another != null){
            ret = orderNum - another.orderNum;
        }
        return ret;
    }
}
