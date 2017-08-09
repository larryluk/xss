package com.larryluk.xss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by larryluk on 2017/8/9.
 */
@RestController
public class SpiderController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello xss";
    }
}
