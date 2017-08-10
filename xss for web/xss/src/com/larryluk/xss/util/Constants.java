package com.larryluk.xss.util;

/**
 * Created by larryluk on 2017/8/10.
 */
public enum Constants {
    RESULT_SUC("0"),
    RESULT_FAIL("1");

    private String s;

    Constants(String s) {

        this.s = s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
