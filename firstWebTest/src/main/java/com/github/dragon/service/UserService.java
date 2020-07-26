package com.github.dragon.service;

import com.github.dragon.bean.PageInfo;
import com.github.dragon.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    int login(String username,String password);

    PageInfo<User> findAll(int currentPage,String username);
/*    List<User> findAll();*/

    void add(User user);

    void deleteById(int id);

    User selectById(int id);

    void update(User user);

    void deleteAll(List<Integer> ids);
}
