package com.larryluk.xss.spider;

import com.larryluk.xss.bean.Book;
import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Index;

import java.util.List;

/**
 * Created by larryluk on 2017/8/10.
 */
public interface Spider {

    /**
     * 爬取小说章节内容
     * @param html
     * @return
     */
    Chapter getChapter(String html);

    /**
     * 爬取小说目录
     * @param html
     * @return
     */
    List<Index> getIndex(String html);

    /**
     * 爬取关键字搜索结果
     *
     * @param html@return
     */
    List<Book> searchBooks(String html);
}
