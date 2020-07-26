package com.github.dragon.filter;

import com.github.dragon.bean.User;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取用户发送的请求
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //提取
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");

        String uri=request.getRequestURI();
        if (user==null&&uri.indexOf("login.do")==-1) {
            //没有进行登录操作时重定向到登录界面
            response.sendRedirect(request.getContextPath()+"../login.jsp");
        }else {
            //进行请求处理
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
