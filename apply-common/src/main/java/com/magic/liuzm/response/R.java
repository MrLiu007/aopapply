package com.magic.liuzm.response;

import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/11/7 21:08
 * @description 统一返回
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private T data;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    private R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> data(int code, T data, String msg) {
        return new R(code, data, data == null ? "暂无数据" : msg);
    }

    public static <T> R<T> success(String msg) {
        return new R(BizJson.DEFAULT_SUCCESS_CODE, msg);
    }
    public static <T> R<T> fail(String msg) {
        return new R(BizJson.DEFAULT_FAILURE_CODE, msg);
    }
}