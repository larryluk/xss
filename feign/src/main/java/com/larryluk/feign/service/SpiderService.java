package com.larryluk.feign.service;

import com.larryluk.feign.bean.Chapter;
import com.larryluk.feign.bean.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther larryluk
 **/
@FeignClient(value = "service-spider")
public interface SpiderService {

    @RequestMapping(value = "/getXsChapter")
    Result<Chapter> getXsChapter(@RequestParam("url") String url);

    @RequestMapping(value = "/getXsIndex")
    Result<?> getXsIndex(@RequestParam("url") String url);

    @RequestMapping(value = "/searchXs")
    Result<?> searchXs(@RequestParam("key") String key);
}
