package com.larryluk.xss.util;

/**
 * Created by larryluk on 2017/8/14.
 */
public enum ResultEnum {
    UNSUPPORT("1", "不支持的网站");
    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return new StringBuilder("{\"code\":\"").append(this.code)
                .append("\", \"msg\":\"").append(this.msg)
                .append("\"}").toString();
    }
}
