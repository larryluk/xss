package com.larryluk.xss.controller;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Result;
import com.larryluk.xss.service.IChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by larryluk on 2017/8/9.
 */
@RestController
public class XsChapterController {
    Logger logger = LoggerFactory.getLogger(XsChapterController.class);

    @Autowired
    private IChapterService chapterService;

    /**
     * 获取小说章节
     * @param url 章节URL
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getXsChapter")
    public Result<Chapter> getXsChapter(@RequestParam("url") String url) throws IOException {
        return chapterService.getXsChapter(url);
    }

    /**
     * 获取小说目录
     * @param url 小说目录对应的URL
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getXsIndex")
    public Result<?> getXsIndex(@RequestParam("url") String url) throws IOException {
        return chapterService.getXsIndex(url);
    }

    /**
     * 搜索小说
     * @param key 搜索关键字
     * @return
     * @throws IOException
     */
    @RequestMapping("/searchXs")
    public Result<?> searchXs(@RequestParam("key") String key) throws IOException {
        return chapterService.searchXsByKey(key);
    }

}
