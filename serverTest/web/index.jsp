<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/7/21
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>登录界面</h1>
    <form action="/login" method="post">
      name:<input name="name" type="text">
      password:<input name="password" type="password">
      <input type="submit" value="登录">
    </form>
  </body>
</html>
