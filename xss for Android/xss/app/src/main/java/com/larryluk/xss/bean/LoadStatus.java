package com.larryluk.xss.bean;

/**
 * Created by Larry on 2017/8/5.
 */

public class LoadStatus {
    private String msgCode;
    private String msgContent;

    public LoadStatus(String msgCode, String msgContent) {
        this.msgCode = msgCode;
        this.msgContent = msgContent;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
