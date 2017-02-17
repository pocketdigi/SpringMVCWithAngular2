package com.pocketdigi.demo.common.util;


import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by fhp on 2017/2/17.
 */
public class FileUtil {

    private static HashMap<String, String> CONTENT_TYPE_EXT_MAP = new HashMap<>(100);

    static {
        CONTENT_TYPE_EXT_MAP.put("application/pdf", ".pdf");
        CONTENT_TYPE_EXT_MAP.put("application/x-png", ".png");
        CONTENT_TYPE_EXT_MAP.put("image/png", ".png");
        CONTENT_TYPE_EXT_MAP.put("application/x-jpg", ".jpg");
        CONTENT_TYPE_EXT_MAP.put("image/jpeg", ".jpg");
        CONTENT_TYPE_EXT_MAP.put("video/mpeg4", ".mp4");
        CONTENT_TYPE_EXT_MAP.put("audio/mp3", ".mp3");
        CONTENT_TYPE_EXT_MAP.put("video/mpg", ".mpeg");
        CONTENT_TYPE_EXT_MAP.put("application/vnd.ms-excel", ".xls");
        CONTENT_TYPE_EXT_MAP.put("application/x-bmp", ".bmp");
        CONTENT_TYPE_EXT_MAP.put("text/html", ".html");
        CONTENT_TYPE_EXT_MAP.put("application/msword", ".doc");
    }

    /**
     * ContentType转扩展名
     *
     * @param contentType 如application/x-png png
     * @return 扩展名 带.
     */
    public static String contentType2Ext(String contentType) {
        return CONTENT_TYPE_EXT_MAP.get(contentType);
    }

    /**
     * 通过扩展名获取contentType
     * @param ext 带.
     * @return
     */
    public static String ext2ContentType(String ext) {
        Set<Map.Entry<String, String>> entries = CONTENT_TYPE_EXT_MAP.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if(entry.getValue().equals(ext))
                return entry.getKey();
        }
        return null;
    }

    public static String generateFileName(String ext) {
        int nextInt = new Random().nextInt(10000);
        return System.currentTimeMillis()+""+nextInt+ext;

    }

    public static void main(String[] args) {

        System.out.println(contentType2Ext("video/mpg"));
    }
}
