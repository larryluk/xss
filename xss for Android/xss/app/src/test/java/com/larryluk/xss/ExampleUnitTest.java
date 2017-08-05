package com.larryluk.xss;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.mvp.model.MainModel;
import com.larryluk.xss.mvp.model.impl.MainModelImpl;

import org.junit.Test;

import java.io.IOException;

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
        MainModel mainModel = new MainModelImpl();
        mainModel.getChapter(new Subscriber<Chapter>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Chapter chapter) {
                System.out.println(chapter);
            }
        }, "http://www.biquzi.com/11_11850/7644114.html");
    }
}