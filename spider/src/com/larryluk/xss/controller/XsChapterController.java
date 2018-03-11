package com.larryluk.xss.controller;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Result;
import com.larryluk.xss.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by larryluk on 2017/8/9.
 */
@RestController
public class XsChapterController {

    @Autowired
    private IChapterService chapterService;

    @RequestMapping(value = "/getXsChapter")
    public Result<Chapter> getXsChapter(@RequestParam("url") String url) throws IOException {
        System.out.println("getXsChapter");
        return chapterService.getXsChapter(url);
    }

    @RequestMapping(value = "/getXsIndex")
    public Result<?> getXsIndex(@RequestParam("url") String url) throws IOException {
        return chapterService.getXsIndex(url);
    }

    @RequestMapping("/searchXs")
    public Result<?> searchXs(@RequestParam("key") String key) throws IOException {
        return chapterService.searchXsByKey(key);
    }

}
