package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;

/**
 * Created by Larry on 2017/8/5.
 */

public class XSChapter {

    private XSSpider resolver;

    public XSChapter(XSSpider resolver) {
        this.resolver = resolver;
    }

    public Chapter getChapter(String html) {
        return resolver.resolver(html);
    }
}
