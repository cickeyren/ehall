package com.easycomp.enums;

/**
 * 错误枚举
 * @author rensq
 * @create 2020-02-18
 */
public enum Error {
    操作失败(1, "操作失败"),
    参数为空异常(2, "参数为空异常"),
    参数异常(3, "参数异常"),
    参数绑定异常(4, "参数绑定异常"),
    请求方式错误(5, "请求方式不支持"),
    请求路径异常(6, "请检查url是否正确"),
    权限不足(7, "权限不足"),
    权限校验异常(8, "权限校验异常"),
    登录失败(9, "登录失败"),
    数据加密异常(10, "数据加密异常"),
    数据解析异常(11, "数据解析异常"),
    Http接口响应异常(12, "Http接口未响应"),
    请求凭证有误(13, "请求凭证有误"),
    请求凭证为空异常(14, "请求凭证为空异常"),
    请求凭证校验失败(15, "请求凭证校验失败"),
    生成签名失败(16, "生成签名失败"),
    请求凭证过期(17, "请求凭证过期"),
    数据库操作异常(18, "操作数据库出现异常"),



    输入输出异常(97, "输入/输出异常"),
    系统运行异常(98, "系统运行异常"),
    系统异常(99, "系统异常");

    private int code;
    private String errMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    Error(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }
}
