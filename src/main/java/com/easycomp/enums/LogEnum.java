package com.easycomp.enums;

/**
 * 本地日志枚举
 * @author rensq
 * @create 2020-02-18
 */
public enum LogEnum {

    ADD("ADD", "新增"),
    UPDATE("UPDATE", "修改"),
    DELETE("DELETE", "删除"),
    GRANT("GRANT", "授权"),
    RUN("RUN", "执行"),
    CHANGE("CHANGE", "更改状态"),
    LOGIN("LOGIN", "登录"),
    LOGOUT("LOGOUT", "退出"),

    OTHER("OTHER", "其它");


    private String category;
    private String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    LogEnum(String category, String description) {
        this.category = category;
        this.description = description;
    }
}