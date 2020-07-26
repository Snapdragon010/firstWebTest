package com.github.drogon.dao;

import com.github.drogon.model.User;
import com.github.drogon.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User selectByName(String name)
    {
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        Connection conn=null;
        DBUtil util=new DBUtil();
        User user=new User();
        try {
            conn =util.getConnection ();
            pstmt=conn.prepareStatement("select * from user where name=?");
            pstmt.setString(1,name);

            rs=pstmt.executeQuery();
            //找到的元素进行排序
            while (rs.next()){
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAge(rs.getString(4));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
                util.closeAll(rs,pstmt,conn);
        }
        return user;
    }

}
