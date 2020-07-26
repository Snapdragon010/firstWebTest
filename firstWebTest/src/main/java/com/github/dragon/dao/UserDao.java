package com.github.dragon.dao;

import com.github.dragon.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User findByUserName(String username);

    List<User> findAll(@Param("start") int start,@Param("username") String username);
//    List<User> findAll();

    void add(User user);

    void deleteById(int id);

    User selectById(int id);

    void update(User user);

    void deleteAll(@Param("ids")List<Integer> ids);

    //分页与搜索功能统一设计；获取总行数
    int getTotalCount(@Param("username") String username);
}
