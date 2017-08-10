package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;

/**
 * Created by larryluk on 2017/8/10.
 */
public interface Spider {

    Chapter getChapter(String html);
}
