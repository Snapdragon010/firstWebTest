package com.github.drogon.servlet;

import com.github.drogon.model.User;
import com.github.drogon.service.UserSevice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserSevice userSevice=new UserSevice();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {


            String name =req.getParameter("name");
            String password=req.getParameter("password");

            User user=userSevice.selectByName(name);
            if (password.equals(user.getPassword())){
                resp.getWriter().write("success");
            }else {
                resp.getWriter().write("failed");
            }
        }

}
