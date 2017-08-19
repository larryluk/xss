package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;

import java.util.List;

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

    public List<Index> getIndex(String html, String base) {
        return resolver.getIndex(html, base);
    }
}
