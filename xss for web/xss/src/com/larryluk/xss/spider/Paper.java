package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Result;
import com.larryluk.xss.util.Constants;

/**
 * Created by larryluk on 2017/8/10.
 */
public class Paper {
    private Spider spider;

    public Paper(Spider spider) {
        this.spider = spider;
    }

    public Result<Chapter> getResult(String html){
        Chapter chapter = spider.getChapter(html);
        return new Result<Chapter>(Constants.RESULT_SUC.toString(), chapter);
    }
}
