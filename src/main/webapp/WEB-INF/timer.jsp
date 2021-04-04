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
<%
  String eventId = request.getAttribute("eventId") == null
    ? ""
    : request.getAttribute("eventId").toString();
  String userId = request.getAttribute("userId").toString();
  String username = request.getAttribute("username").toString();
%>
<body>
<h2><%=username%></h2>
<div>Welcome, more features to come...</div>
<form action="Timer">
  <input type="hidden" name="userId" value=<%=userId%>>
  <input type="hidden" name="username" value=<%=username%>>
  <input type="submit" value="Start">
</form>
<% if (!eventId.isEmpty()) {%>
<div>Timer started with id: <%=eventId%>
</div>
<%}%>
</body>
</html>