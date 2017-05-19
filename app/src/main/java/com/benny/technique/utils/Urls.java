package com.benny.technique.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 描述：请求IP
 * Created by Benny on 2017/2/21.
 * PackageName com.benny.technique.utils
 */

public class Urls {
    public static String BAIKE="http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?";
    //scope=103&format=json&appid=379020&bk_key=关键字&bk_length=600

    public static String getUrl(String url, Map<String, String> paras)
    {

        StringBuilder sb = new StringBuilder(url);
        if (paras != null && !paras.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                sb.append(entry.getKey()).append("=");
                try {
                    sb.append(URLEncoder.encode(entry.getValue(), "utf-8"));//数据未加密

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
