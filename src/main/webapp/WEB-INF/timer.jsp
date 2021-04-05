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
<%! private String valueOrEmpty(HttpServletRequest request, String attrName) {
  return request.getAttribute(attrName) == null
    ? ""
    : request.getAttribute(attrName).toString();
}
%>
<%
  String eventId = valueOrEmpty(request, "eventId");
  String userId = valueOrEmpty(request, "userId");
  String username = valueOrEmpty(request, "username");
  String timerId = valueOrEmpty(request, "timerId");
  String timerValue = valueOrEmpty(request, "timerValue");
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
<div class="card">
  <form action="EditTimer">
    <div><%=timerValue%></div>
    <input type="text" name="newTimerValue">
    <input type="hidden" name="userId" value=<%=userId%>>
    <input type="hidden" name="username" value=<%=username%>>
    <input type="hidden" name="timerId" value=<%=timerId%>>
    <input type="submit" value="Edit">
  </form>
</div>
</body>
</html>