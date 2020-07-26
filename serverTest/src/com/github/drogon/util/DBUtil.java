package com.github.drogon.util;

import java.sql.*;

public class DBUtil
{
    public static Connection getConnection () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //获取数据库链接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/asd?useSSL=false","root","123456");

        return conn;

    }

    public static void closeAll (ResultSet rs, Statement stmt,Connection conn)
    {
        if (rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt!=null)
        {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
