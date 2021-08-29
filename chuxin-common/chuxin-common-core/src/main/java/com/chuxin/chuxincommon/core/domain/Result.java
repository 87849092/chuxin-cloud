package com.chuxin.chuxincommon.core.domain;

import com.chuxin.chuxincommon.core.constant.Constants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 初心
 * @date 2021/8/29 9:52
 */
@Getter
@Setter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3461393616838083473L;

    /** 成功 */
    public static final int SUCCESS = Constants.SUCCESS;

    /** 失败 */
    public static final int FAIL = Constants.FAIL;

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return restResult(null, SUCCESS, null);
    }
    public static <T> Result<T> success(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> Result<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> Result<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> Result<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> Result<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg)
    {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
