package com.ins.myapplication4.app.client;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 15-7-28.
 */
public class ClientDiscoverAPI {
    public static final String SERVER_MOBILE = "http://mobile.ximalaya.com";

    private ClientDiscoverAPI(){

    }

    ////////////////////////////////////////

    /**
     * 获取分类Tag菜单<br/>
     * 调用接口：http://mobile.ximalaya.com/m/category_tag_menu <br/>
     * 请求方法：GET<br/>
     *
     * @param type 可选 默认是 user
     * @return
     */
    public static String getCategoryTagMenu(String type){
        String ret = null;
        String url = null;
        StringBuilder sb = new StringBuilder();
        sb.append(SERVER_MOBILE);
        sb.append("/m/category_tag_menu");
        if (type != null) {
            sb.append("?type=").append(type);
            sb.append("?device=android");
        }
        url = sb.toString();
        sb = null;

        byte[] data = HttpUtil.doGet(url);

        if (data != null){
            try {
                ret  = new String(data,"utf-8");
            } catch (UnsupportedEncodingException e) {
                ret = new String(data);
            }
        }
        return ret;
    }
}
