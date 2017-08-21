package com.larryluk.xss;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.mvp.model.MainModel;
import com.larryluk.xss.mvp.model.impl.MainModelImpl;
import com.larryluk.xss.spider.BiQuSpider;
import com.larryluk.xss.spider.XSChapter;
import com.larryluk.xss.spider.XSSpider;
import com.larryluk.xss.util.HttpRequestUtil;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSpider() throws IOException {
        Chapter chapter = new Chapter();
        chapter.setNowUrl("http://www.biquzi.com/11_11850/7644114.html");
        System.out.println(chapter.getIndexUrl());
    }

    @Test
    public void testGetIndex() throws IOException {
        String url = "http://www.biquzi.com/11_11850";
        String html = HttpRequestUtil.getHtml(url);
        XSSpider biQuSpider = new BiQuSpider();
        List<Index> index = new XSChapter(biQuSpider).getIndex(html, url);
        for(Index i : index) {
//            System.out.println(i);
            System.out.println(i.getUrl());
        }

    }
}