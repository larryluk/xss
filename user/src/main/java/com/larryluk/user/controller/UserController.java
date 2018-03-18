package com.larryluk.user.controller;

import com.larryluk.user.bean.Result;
import com.larryluk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther larryluk
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public Result<?> test() {
        return userService.selectUserById(1);
    }
}
