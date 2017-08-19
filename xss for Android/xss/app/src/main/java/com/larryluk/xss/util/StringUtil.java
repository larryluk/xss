package com.larryluk.xss.util;

/**
 * Created by Larry on 2017/8/13.
 */

public class StringUtil {
    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s) ? true : false;
    }
}
