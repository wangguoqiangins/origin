package com.ins.myapplication4.app.parsers;

import com.ins.myapplication4.app.model.CategoryTagMenu;
import com.ins.myapplication4.app.model.DiscoverCategory;
import com.ins.myapplication4.app.model.DiscoverTab;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 15-7-28.
 */
public class DataParser {
    private DataParser(){

    }


    /**
     * 解析发现的分类
     * @param json
     * @return
     */
    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject json){
        List<DiscoverCategory> ret = null;
        if (json != null) {

            try {
                int code = json.getInt("ret");

                if(code == 0){

                    JSONArray jsonArray = json.getJSONArray("list");

                    int len = jsonArray.length();

                    if(len > 0){

                        ret = new LinkedList<DiscoverCategory>();

                        for (int i = 0; i < len; i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            DiscoverCategory category = new DiscoverCategory();

                            category.parseJSON(jsonObject);

                            ret.add(category);

                        }

                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    /**
     * 解析 CategoryTagMenuTask 返回的JSON结果
     * @param json JSONObject
     * @return List&lt;CategoryTagMenu&gt;
     */
    public static List<CategoryTagMenu> parseCategoryTagMenuResult(JSONObject json){
        List<CategoryTagMenu> ret = null;
        if (json != null) {
            try {
                int code = json.getInt("ret");

                if(code == 0){

                    JSONObject data = json.getJSONObject("data");

                    int category_count = data.getInt("category_count");

                    if(category_count > 0){

                        JSONArray array = data.getJSONArray("category_list");

                        category_count = array.length();

                        if(category_count > 0){

                            ret = new LinkedList<CategoryTagMenu>();

                            for (int i = 0; i < category_count; i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                CategoryTagMenu menu = new CategoryTagMenu();

                                // 实体类，自己解析自己的数据
                                menu.parseJSON(jsonObject);

                                ret.add(menu);
                            }

                        }

                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 解析发现的Tab标题
     * @param jsonObject
     * @return
     */
    public static List<DiscoverTab> parseDiscoverTabs(JSONObject jsonObject) {
        List<DiscoverTab> ret = null;

        try {
            int code = jsonObject.getInt("ret");
            if(code == 0){

                JSONObject tabs = jsonObject.getJSONObject("tabs");

                JSONArray jsonArray = tabs.optJSONArray("list");

                if (jsonArray != null) {

                    int len = jsonArray.length();

                    if(len > 0){

                        ret = new LinkedList<DiscoverTab>();

                        for (int i = 0; i < len; i++) {

                            DiscoverTab tab = new DiscoverTab();

                            tab.parseJSON(jsonArray.getJSONObject(i));

                            ret.add(tab);

                        }

                        // TODO 更新ViewPager与TabLayout

                    }

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;

    }
}
