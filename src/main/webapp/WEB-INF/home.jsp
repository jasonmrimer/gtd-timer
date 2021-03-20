<%--
  Created by IntelliJ IDEA.
  User: engineer
  Date: 3/18/21
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link
  rel="stylesheet"
  href="../style.css"
>
<html>
<jsp:include page="../header.jsp"/>
<body>
<h2><%=request.getAttribute("username")%></h2>
<div>Welcome, more features to come...</div>
</body>
</html>