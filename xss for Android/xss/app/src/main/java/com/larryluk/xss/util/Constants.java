package com.larryluk.xss.util;

/**
 * Created by Larry on 2017/8/5.
 */

public enum Constants {
    LOAD_SUC("0"),
    LOAD_SUC_MSG("加载成功"),
    LOAD_FAIL("1"),
    LOAD_FAIL_MSG("加载失败"),
    WAIL_MSG("疯狂加载中，请稍等。");

    private String s;
    private Constants(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
