<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<!DOCTYPE html>
<html>
<head>
  <title>GTD Timer</title>
</head>
  <%
  String error = request.getAttribute("error") != null
    ? request.getAttribute("error").toString()
    : "";
%>
<body>
<jsp:include page="WEB-INF/login.jsp">
  <jsp:param name="error" value="<%=error%>"/>
</jsp:include>
</body>
<style type="text/css">
</style>
