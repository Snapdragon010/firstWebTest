package com.github.drogon.service;

import com.github.drogon.dao.UserDao;
import com.github.drogon.model.User;

public class UserSevice {
    UserDao userDao=new UserDao();

    public User selectByName(String name)
    {
        return userDao.selectByName(name);
    }

}
