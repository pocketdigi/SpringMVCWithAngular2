package com.pocketdigi.demo.dto;

import lombok.Data;

/**
 * Created by fhp on 16/10/30.
 */
@Data
public class ResultDTO<T> implements java.io.Serializable{
    String msg;
    int code;
    T data;
    public static <T> ResultDTO<T> wrapSuccess(T data) {
        ResultDTO<T> result = new ResultDTO<T>();
        result.data = data;
        result.code=0;
        result.msg="成功";
        return result;
    }

    public static <T> ResultDTO<T> wrapError(int code,String msg) {
        ResultDTO<T> result = new ResultDTO<T>();
        result.code=code;
        result.msg=msg;
        return result;
    }
}
