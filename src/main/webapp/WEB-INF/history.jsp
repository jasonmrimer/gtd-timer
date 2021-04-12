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
      return "+ " + event.getElapsed() + " sec";
    }

    if (event.getElapsed() < -600000000) {
      return "—";
    }

    return "- " + Math.abs(event.getElapsed()) + " sec";
  }
%>
<%
  events = (ArrayList<EventModel>) request.getAttribute("events");
  dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
%>
<body>
<div class="table-container">
  <div class="table-headings">
    <span class="table-heading column-1">Date</span>
    <span class="table-heading column-2">Goal</span>
    <span class="table-heading column-3">Elapsed Time</span>
  </div>
  <% for (EventModel event : events) {%>
  <div class="table-row">
    <span class="cell column-1"><%=dateTimeFormatter.format(event.getDateTime())%></span>
    <span class="cell column-2"><%=event.getGoal()%> sec</span>
    <span class="cell column-3"><%=elapsedOverUnder(event)%></span>
    <form class="form-delete" action="History" method="post">
      <input type="hidden" name="eventId" value=<%=event.getId()%>>
      <button class="btn-lg button-secondary button-delete" type="submit">
        <img class="button-icon" src="../icon-delete.svg" alt="icon delete"/>
        <span class="button-text">Delete</span>
      </button>
    </form>
  </div>
  <% }%>
</div>
</body>
</html>
<style type="text/css">

  .form-delete {
    margin-left: 16px;
    margin-bottom: 0;
  }

  .table-container {
    margin-top: 32px;
  }

  .table-headings, .table-row {
    display: flex;
    flex-direction: row;
  }

  .column-1 {
    width: 104px;
  }

  .column-2 {
    width: 72px;
  }

  .column-3 {
    width: 96px;
  }

  .table-heading, .cell {
    display: flex;
    justify-content: flex-end;
    margin: 0 8px;
  }

  .table-row {
    display: flex;
    align-items: center;
    height: 40px;
    margin-bottom: 24px;
  }

  .table-heading {
    font-family: Roboto, sans-serif;
    font-style: normal;
    font-weight: 500;
    font-size: 14px;
    line-height: 24px;
    display: flex;
    align-items: center;
    text-align: right;
    letter-spacing: 0.1px;
    color: #000000;
  }

  td {
    text-align: right;
  }

  .cell {
    font-family: Roboto, sans-serif;
    font-style: normal;
    font-weight: normal;
    font-size: 12px;
    line-height: 16px;
    display: flex;
    align-items: center;
    text-align: right;
    letter-spacing: 0.4px;
    color: #000000;
  }

  .button-secondary {
    border: none;
    padding: 4px 8px;
    display:flex;
    flex-direction: row;
    align-items: center;
    width: 104px;
    height: 32px;
    background: #FAFAFA;
    border-radius: 4px;
    filter: drop-shadow(0 1px 1px rgba(0, 0, 0, 0.14)) drop-shadow(0 2px 1px rgba(0, 0, 0, 0.12)) drop-shadow(0px 1px 3px rgba(0, 0, 0, 0.2));
  }

  .button-secondary > .button-text {
    font-family: Roboto, sans-serif;
    font-style: normal;
    font-weight: 500;
    font-size: 14px;
    line-height: 16px;
    letter-spacing: 1.25px;
    text-transform: uppercase;
    color: #B00020;
    margin-left: 2px;
  }

  .button-secondary > .button-icon {
    height: 24px;
    width: 24px;
    filter: invert(11%) sepia(86%) saturate(5355%) hue-rotate(342deg) brightness(76%) contrast(107%);
  }

  .button-secondary:hover {
    cursor: pointer;
    background: #B00020;
  }

  .button-secondary:hover > .button-text {
    color: #FFFFFF;
    fill: #FFFFFF;
  }

  .button-secondary:hover > .button-icon {
    filter: invert(100%) sepia(100%) saturate(0%) hue-rotate(249deg) brightness(103%) contrast(103%);
  }
</style>