package com.ins.myapplication4.app.util;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created by Administrator on 15-7-28.
 */
public class StreamUtil {
    private StreamUtil(){

    }

    public static void close(Object stream){
        if (stream != null){

            try {

                if (stream instanceof InputStream){
                    ((InputStream)stream).close();
                }else if (stream instanceof InputStream){
                    ((InputStream)stream).close();
                }else if (stream instanceof OutputStream){
                    ((OutputStream)stream).close();
                }else if (stream instanceof Reader){
                    ((Reader)stream).close();
                }else if (stream instanceof Writer){
                    ((Writer)stream).close();
                }else if (stream instanceof HttpURLConnection){
                    ((HttpURLConnection)stream).disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 将输入流中的数据，读出来存储在自己的数组中
     * @param in
     * @return
     */
    public static byte[] readStream(InputStream in) throws IOException {
        byte[] ret = null;
        if (in != null){
            byte[] buf = new byte[128];
            int len;

            ByteArrayOutputStream bout  = null;

            bout = new ByteArrayOutputStream();

            while (true){
                len = in.read(buf);
                if (len == -1){
                    break;
                }
                bout.write(buf,0,len);
            }

            buf = null;
            ret = bout.toByteArray();
            bout.close();

        }
        return ret;
    }
}
