package com.ycicic.ysm.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author ycicic
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -338818066492385802L;

    public static final Integer SUCCESS = 200;
    public static final String SUCCESS_MSG = "操作成功";
    public static final int FAIL = 500;
    public static final String FAIL_MSG = "操作失败";

    private Integer code;
    private String msg;
    private T data;

    public static <T> Response<T> success() {
        return restResult(null, SUCCESS, SUCCESS_MSG);
    }

    public static <T> Response<T> success(T data) {
        return restResult(data, SUCCESS, SUCCESS_MSG);
    }

    public static <T> Response<T> success(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> Response<T> fail() {
        return restResult(null, FAIL, FAIL_MSG);
    }

    public static <T> Response<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> Response<T> fail(T data) {
        return restResult(data, FAIL, FAIL_MSG);
    }

    public static <T> Response<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> Response<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> Response<T> restResult(T data, int code, String msg) {
        Response<T> apiResult = new Response<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
