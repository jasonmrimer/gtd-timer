<%@ page import="java.net.http.HttpRequest" %><%--
  Created by IntelliJ IDEA.
  User: engineer
  Date: 2/6/21
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link
  rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
  crossorigin="anonymous"
>
<link
  rel="stylesheet"
  href="style.css"
>
<%!
  private String classNameForCurrentPage(String page, HttpServletRequest req) {
    String currentPage = req.getSession().getAttribute("currentPage").toString();
    String className = "page";
    if (currentPage.equalsIgnoreCase(page)) {
      className = "page page-current";
    }
    return className;
  }
%>
<%
  String username = request.getSession().getAttribute("username") != null
    ? request.getSession().getAttribute("username").toString()
    : "";
%>
<html>
<head>
  <title>GTD Timer</title>
</head>
<body>
<div class="banner">
  <h6 class="title-app">GTD Timer</h6>
  <%if (request.getSession().getAttribute("currentPage") != null) {%>
  <div class="menu-options">
    <form
      action="Page"
      method="post"
      class="page-form"
    >
      <input
        type="submit"
        value="Timer"
        class="page-left <%=classNameForCurrentPage("timer", request)%>"
      />
    </form>
    <form
      action="Page"
      method="get"
      class="page-form"
    >
      <input
        type="submit"
        value="History"
      class="page-right <%=classNameForCurrentPage("history", request)%>"
      />
    </form>
  </div>
  <%}%>
  <%if (!username.isEmpty()) {%>
  <div class="container-profile">
    <span class="username"><%=username%></span>
    <img class=icon-user" src="./icon-user.svg" alt="user profile icon"/>
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
  }

  h6 {
    padding: 16px;
    color: #FEFEFE;
    background: none;
  }

  .container-profile {
    padding: 16px;
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

  .menu-options {
    display: flex;
    flex-direction: row;
    padding-bottom: 2px;
  }

  .page-form {
    margin: 0;
    height: 100%;
  }

  .page {
    display: flex;
    align-items: center;
    height: 100%;

    border-top: none;
    border-right: none;
    border-left: none;
    border-bottom: 2px solid #6200EE;
    background: none;

    font-family: Roboto, sans-serif;
    font-style: normal;
    font-weight: 500;
    font-size: 14px;
    line-height: 16px;

    text-align: center;
    letter-spacing: 1.25px;
    text-transform: uppercase;

    color: #FFFFFF;
  }

  .page:hover {
    cursor: pointer;
  }

  .page-left {
    margin-right: 16px;
  }

  .page-current {
    border-bottom: 2px solid #FFFFFF;
  }
</style>
