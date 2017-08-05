package com.larryluk.xss.mvp.model.impl;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.mvp.model.MainModel;
import com.larryluk.xss.spider.BiQuSpider;
import com.larryluk.xss.spider.XSChapter;
import com.larryluk.xss.spider.XSSpider;
import com.larryluk.xss.util.Constants;
import com.larryluk.xss.util.HttpRequestUtil;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Larry on 2017/8/5.
 */

public class MainModelImpl implements MainModel {

    /**
     * 爬取小说章节
     *
     * @param subscriber
     * @param url
     */
    @Override
    public void getChapter(Subscriber<Chapter> subscriber, String url) {
        Observable.just(url)
                .map(new Func1<String, Chapter>() {
                    @Override
                    public Chapter call(String s) {
                        Chapter chapter = null;
                        try {
                            String html = HttpRequestUtil.getHtml(s);
                            XSSpider resolver = new BiQuSpider();
                            chapter = new XSChapter(resolver).getChapter(html);

                        } catch (IOException e) {
                            e.printStackTrace();
                            chapter = new Chapter(Constants.LOAD_FAIL.toString(), Constants.LOAD_FAIL_MSG.toString());
                        }

                        return chapter;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
