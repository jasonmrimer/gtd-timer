<%--
  Created by IntelliJ IDEA.
  User: engineer
  Date: 2/6/21
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link
  rel="stylesheet"
  href="style.css"
>
<%
  String username = request.getAttribute("username") != null
    ? request.getAttribute("username").toString()
    : "";
%>
<html>
<head>
  <title>GTD Timer</title>
</head>
<body class="header">
<div class="banner">
  <h6 class="title-app">GTD Timer</h6>
  <%if (!username.isEmpty()) {%>
  <div class="container-profile">
    <span class="username"><%=username%></span>
    <img class=icon-user" src="icon-user.svg" alt="user profile icon"/>
  </div>
  <%}%>
</div>
</body>
</html>
<style type="text/css">
  .banner {
      width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      height: 52px;
      background: #6200EE;
      padding: 16px;
  }

  h6  {
      color: #FEFEFE;
      background: none;
  }

  .container-profile {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: flex-end;
  }

  .username {
      margin-right: 4px;
      text-align: right;
      color: #FFFFFF;
      opacity: 74%;
  }
</style>
