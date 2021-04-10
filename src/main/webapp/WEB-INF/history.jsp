<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.louisville.cse640.rimer.controllers.EventModel" %><%--
  Created by IntelliJ IDEA.
  User: engineer
  Date: 4/10/21
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../header.jsp"/>
<%!
  ArrayList<EventModel> events;
%>
<%
  events = (ArrayList<EventModel>) request.getAttribute("events");
%>
<body>
<div><%=events%></div>
</body>
</html>
