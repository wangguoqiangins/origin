package com.ins.myapplication4.app.client;

import android.os.Build;
import com.ins.myapplication4.app.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by Administrator on 15-7-28.
 */
public class HttpUtil {
    public static final int TIMEOUT_CONNECT = 5000;
    public static final int TIMEOUT_READ = 5000;

    private HttpUtil(){

    }

    ///////////////////////////////

    /**
     * 获取GET请求，返回字节数组
     * @param url String url
     * @return byte[]
     */
    public static byte[] doGet(String url){
        byte[] ret = null;

        if (url != null) {

            // 注意释放连接

            HttpURLConnection conn = null;

            InputStream inputStream = null;

            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET"); // GET 请求
                conn.setRequestProperty("Accept-Encoding","gzip"); // 设置 HTTP 请求头
                conn.setRequestProperty("User-Agent","ting_4.1.7(MI2,"+ Build.VERSION.SDK_INT+")");

                // 设置联网超时，只能够用于短时间的联网操作
                // 长时间的自己再重写开发

                // Socket打开连接的时间
                conn.setConnectTimeout(TIMEOUT_CONNECT);
                // 连接一共可以读多久
                conn.setReadTimeout(TIMEOUT_READ);

                ////////////////////////////
                // 连接的属性 302是重定向

                // 设置自动处理 302/307 跳转 通常返回200
                conn.setInstanceFollowRedirects(true);

                // 连接
                conn.connect();

                // 状态码
                int code = conn.getResponseCode();

                if (code == 200){

                    inputStream = conn.getInputStream();

                    // 获取服务器头信息，内容是否压缩

                    // 获取指定的服务器返回的头信息
                    String contentEncoding = conn.getHeaderField("Content-Encoding");

                    if (contentEncoding == null) {
                        contentEncoding = conn.getHeaderField("content-encoding");
                    }

                    if (contentEncoding != null && contentEncoding.equals("gzip")) {
                        // 代表数据经过压缩
                        // 使用GZIPInputStream 解压缩
                        inputStream = new GZIPInputStream(inputStream);
                    }

                    // 读 inputStream
                    ret = StreamUtil.readStream(inputStream);

                }else {
                    // TODO 其他情况
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(inputStream);
                StreamUtil.close(conn);
            }
        }

        return null;
    }
}
