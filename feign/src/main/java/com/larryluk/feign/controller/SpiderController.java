package com.larryluk.feign.controller;

import com.larryluk.feign.bean.Chapter;
import com.larryluk.feign.bean.Result;
import com.larryluk.feign.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @auther larryluk
 **/
@RestController
public class SpiderController {

    @Autowired
    private SpiderService spiderService;

    @RequestMapping(value = "/getXsChapter")
    public Result<Chapter> getXsChapter(@RequestParam("url") String url) throws IOException {
        return spiderService.getXsChapter(url);
    }

    @RequestMapping(value = "/getXsIndex")
    public Result<?> getXsIndex(@RequestParam("url") String url) throws IOException {
        return spiderService.getXsIndex(url);
    }

    @RequestMapping(value = "/searchXs")
    public Result<?> searchXs(@RequestParam("key") String key) throws IOException {
        return spiderService.searchXs(key);
    }
}
