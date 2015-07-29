package com.ins.myapplication4.app.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 15-7-28.
 */
public class CategoryTagMenu {
    private int id;

    private String name;

    private String title;

    private String intro;

    /**
     * 对应 is_display
     */
    private String display;

    private String coverpath;

    private List<String> tagList;

    /**
     * 所有实体类都会包含这个名称的方法，用于解析JSON
     * @param jsonObject
     */
    public void parseJSON(JSONObject jsonObject) throws JSONException {

        if (jsonObject != null) {
            id = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            // 必须存在的内容
            title = jsonObject.getString("title");
            // 可选内容
            intro = jsonObject.getString("intro");
            // 可选内容
            display = jsonObject.getString("is_display");

            coverpath = jsonObject.getString("cover_path");

            JSONArray array = jsonObject.optJSONArray("tag_list");

            if (array != null){
                int len = array.length();

                tagList = new LinkedList<String>();

                for (int i = 0; i < len; i++) {

                    tagList.add(array.getString(i));

                }
            }
        }

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public String getDisplay() {
        return display;
    }

    public String isCover_path() {
        return coverpath;
    }

    public List<String> getTagList() {
        return tagList;
    }
}
