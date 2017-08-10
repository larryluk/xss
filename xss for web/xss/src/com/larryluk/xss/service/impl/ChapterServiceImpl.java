package com.larryluk.xss.service.impl;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Result;
import com.larryluk.xss.service.IChapterService;
import com.larryluk.xss.spider.Paper;
import com.larryluk.xss.spider.impl.BiQuSpider;
import com.larryluk.xss.util.HttpRequestUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by larryluk on 2017/8/10.
 */
@Service("chapterService")
public class ChapterServiceImpl implements IChapterService{
    @Override
    public Result<Chapter> getXsChapter(String url) throws IOException {
        String html = HttpRequestUtil.getHtml(url);

        Result<Chapter> result = new Paper(new BiQuSpider()).getResult(html);

        return result;
    }
}
