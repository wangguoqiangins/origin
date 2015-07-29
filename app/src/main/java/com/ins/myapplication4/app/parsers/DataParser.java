package com.ins.myapplication4.app.parsers;

import com.ins.myapplication4.app.model.CategoryTagMenu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 15-7-28.
 */
public class DataParser {
    private DataParser(){

    }

    /**
     * 解析 CategoryTagMenuTask 返回的json结果
     * @param json JSONObject
     * @return List&lt;CategoryTagMenu&gt;
     */
    public static List<CategoryTagMenu> parseCategoryTagMenuResult(JSONObject json){

        List<CategoryTagMenu> ret = null;

        if (json != null){
            try {
                int code = json.getInt("ret");

                if (code == 0){
                    JSONObject data = json.getJSONObject("data");

                    int category_count = data.getInt("category_count");

                    if (category_count > 0){

                        JSONArray array = data.getJSONArray("category_list");

                        category_count = array.length();

                        if (category_count > 0){

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
}
