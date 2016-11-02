package com.pocketdigi.demo.common.converter;


import com.google.gson.Gson;

/**
 * 对象转成json
 * Created by fhp on 16/8/11.
 */
public class JsonConverter {
    public static String convertObj(Object obj) {
        Gson gson=new Gson();
        return gson.toJson(obj);
    }
}
