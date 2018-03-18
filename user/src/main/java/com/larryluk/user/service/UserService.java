package com.larryluk.user.service;

import com.larryluk.user.bean.Result;
import com.larryluk.user.bean.User;

/**
 * @auther larryluk
 **/
public interface UserService {

    Result<User> selectUserById(int id);
}
