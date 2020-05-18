package com.easycomp.dto;

import cn.hutool.core.util.StrUtil;
import com.easycomp.enums.Error;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回restful
 * @author rensq
 * @create 2020-02-18
 */
@Data
@Getter
@Setter
public class R<T>{
    private static final int SUCCESS_CODE = 0;//成功
    private static final int ERROR_CODE = 1;//失败
    private static final String SUCCESS_R = "操作成功";
    private static final String ERROR_R = "操作失败";

    private int code;
    private String msg;
    private T data;

    public R() {
    }

    public static R success() {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(SUCCESS_R);
        return r;
    }

    public static R success(Object data) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(SUCCESS_R);
        r.setData(data);
        return r;
    }

    public static R success(String msg) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        return r;
    }

    public static R success(Object data, String msg) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ERROR_CODE);
        r.setMsg(ERROR_R);
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.setCode(ERROR_CODE);
        r.setMsg(msg);
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static R error(Object data, int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R error(Error error) {
        return error(error.getCode(), error.getErrMsg());
    }

    public static R error(Error error, String tip) {
        if (StrUtil.isNotBlank(tip) || StrUtil.isNotEmpty(tip)) {
            tip = "，" + tip;
        } else {
            tip = "";
        }
        return error(error.getCode(), error.getErrMsg() + tip);
    }

    public static R error(Object data, String msg) {
        return error(data, ERROR_CODE, msg);
    }

    public static R error(Object data, Error error, String msg) {
        if (StrUtil.isNotBlank(msg) || StrUtil.isNotEmpty(msg)) {
            msg = "，" + msg;
        } else {
            msg = "";
        }
        return error(data, error.getCode(), error.getErrMsg() + msg);
    }
}
