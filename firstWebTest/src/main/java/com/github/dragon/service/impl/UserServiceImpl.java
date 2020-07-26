package com.github.dragon.service.impl;

import com.github.dragon.bean.PageInfo;
import com.github.dragon.bean.User;
import com.github.dragon.dao.UserDao;
import com.github.dragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int login(String username, String password) {
        User user=userDao.findByUserName(username);
        if (user!=null&&user.getPassword().equals(password))
        {
            return user.getId();
        }
        return -1;
    }

    @Override
    public PageInfo<User> findAll(int currentPage, String username) {
        PageInfo<User> pageInfo =new PageInfo<>();
        pageInfo.setSize(5);
        //tc为查询到的数据的总行数
        int tc =userDao.getTotalCount(username);
        pageInfo.setTotalCount(tc);
        //tp为总的页数
        int tp=(int)Math.ceil(tc/5.0);
        pageInfo.setTotalPage(tp);
        if (currentPage<1){
            pageInfo.setCurrentPage(1);
        }else if (currentPage>tp) {
            pageInfo.setCurrentPage(tp);
        }else{
            pageInfo.setCurrentPage(currentPage);
        }
        //-1之后第一页的数据从0开始搜(0,5,10,15.....)
        int start=(pageInfo.getCurrentPage()-1)*5;
        List<User> userList=userDao.findAll(start,username);
        pageInfo.setList(userList);


        return pageInfo;
    }

/*    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }*/

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public User selectById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        userDao.deleteAll(ids);
    }
}
