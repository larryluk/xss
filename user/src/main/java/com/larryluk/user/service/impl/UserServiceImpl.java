package com.larryluk.user.service.impl;

import com.larryluk.user.bean.Result;
import com.larryluk.user.bean.User;
import com.larryluk.user.dao.UserDao;
import com.larryluk.user.service.UserService;
import com.larryluk.user.util.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther larryluk
 **/
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public Result<User> selectUserById(int id) {
        User user = userDao.selectByPrimaryKey(id);
        return new Result<User>(Constants.SUCCESS, user);
    }
}
