package com.larryluk.xss.bean;

/**
 * Created by Larry on 2017/8/19.
 */

public class Status {
    String code;
    String msg;

    public Status(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}