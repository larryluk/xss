package com.larryluk.xss.controller;

import com.larryluk.xss.bean.Chapter;
import com.larryluk.xss.bean.Result;
import com.larryluk.xss.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by larryluk on 2017/8/9.
 */
@RestController
public class XsChapterController {

    @Autowired
    private IChapterService chapterService;

    @RequestMapping("/getXsChapter")
    public Result<Chapter> getXsChapter(String url) throws IOException {
        return chapterService.getXsChapter(url);
    }

}
