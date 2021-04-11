<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.louisville.cse640.rimer.controllers.EventModel" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
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
  DateTimeFormatter dateTimeFormatter;

  String elapsedOverUnder(EventModel event) {
    if (event.getElapsed() > 0) {
      return "+ " + event.getElapsed();
    }
    return "- " + Math.abs(event.getElapsed());
  }
%>
<%
  events = (ArrayList<EventModel>) request.getAttribute("events");
  dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
%>
<body>
<table>
  <tr>
    <th scope="col">Date</th>
    <th scope="col">Goal</th>
    <th scope="col">Elapsed Time</th>
  </tr>
<% for (EventModel event : events) {%>
<tr>
  <td><%=dateTimeFormatter.format(event.getDateTime())%></td>
  <td><%=event.getGoal()%> sec</td>
  <td><%=elapsedOverUnder(event)%> sec</td>
</tr>
<% }%>
</table>
</body>
</html>
