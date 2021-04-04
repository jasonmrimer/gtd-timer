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
<% if (eventId.isEmpty()) {%>
<form action="Timer" method="get">
  <input type="hidden" name="userId" value=<%=userId%>>
  <input type="hidden" name="username" value=<%=username%>>
  <input type="submit" value="Start">
</form>
<%}%>
<% if (!eventId.isEmpty()) {%>
<form action="Timer" method="post">
  <input type="hidden" name="userId" value=<%=userId%>>
  <input type="hidden" name="username" value=<%=username%>>
  <input type="hidden" name="eventId" value=<%=eventId%>>
  <input type="submit" value="Stop">
</form>
<%}%>
</body>
</html>