package com.larryluk.xss.service.impl;

import com.larryluk.xss.bean.Book;
import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;
import com.larryluk.xss.bean.Result;
import com.larryluk.xss.service.IChapterService;
import com.larryluk.xss.spider.Spider;
import com.larryluk.xss.util.Constants;
import com.larryluk.xss.util.HttpRequestUtil;
import com.larryluk.xss.util.ResultEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by larryluk on 2017/8/10.
 */
@Service("chapterService")
public class ChapterServiceImpl implements IChapterService{

    @Resource(name = "biquSpider")
    private Spider spider;

    @Value("${constant.search.url}")
    private String searchUrl;

    @Override
    public Result<Chapter> getXsChapter(String url) throws IOException {
        String html = HttpRequestUtil.getHtml(url);
        Chapter chapter = spider.getChapter(html);
        return new Result<Chapter>(Constants.RESULT_SUC.toString(), chapter);
    }

    @Override
    public Result<List<Index>> getXsIndex(String url) throws IOException {
        String html = HttpRequestUtil.getHtml(url);
        List<Index> index = spider.getIndex(html);
        return new Result<List<Index>>(Constants.RESULT_SUC.toString(), index);
    }

    @Override
    public Result<?> searchXsByKey(String key) throws IOException {
        String encodeKey = URLEncoder.encode(key,"gbk");
        String html = HttpRequestUtil.getHtml(searchUrl + encodeKey);
        List<Book> books = spider.searchBooks(html);

        if (books.isEmpty()) {
            List<Index> index = spider.getIndex(html);
            if (!index.isEmpty()) {
                return new Result<>(Constants.SEARCH_SUC_ONLY, index);
            } else {
                return new Result<>(ResultEnum.NOT_BOOK);
            }
        }

        return new Result<>(Constants.SEARCH_SUC_MANY, books);
    }
}
