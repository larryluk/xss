package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;

/**
 * Created by Larry on 2017/8/5.
 */

public interface XSSpider {

    /**
     * 解析html
     * @param s
     * @return
     */
    Chapter resolver(String s);
}
