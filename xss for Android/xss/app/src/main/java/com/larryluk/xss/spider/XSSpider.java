package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;

import java.util.List;

/**
 * Created by Larry on 2017/8/5.
 */

public interface XSSpider {

    /**
     * 解析html
     * @param html
     * @return
     */
    Chapter resolver(String html);

    List<Index> getIndex(String html, String baseUrl);
}
