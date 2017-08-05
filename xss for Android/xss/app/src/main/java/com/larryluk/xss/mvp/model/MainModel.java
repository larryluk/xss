package com.larryluk.xss.mvp.model;

import com.larryluk.xss.bean.Chapter;

import rx.Subscriber;

/**
 * Created by Larry on 2017/8/5.
 */

public interface MainModel {
    /**
     * 爬取小说章节
     */
    void getChapter(Subscriber<Chapter> subscriber, String url);
}
