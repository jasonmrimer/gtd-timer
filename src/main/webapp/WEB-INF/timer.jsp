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
<%!
  private String valueOrEmpty(HttpServletRequest request, String attrName) {
    String value = "";
    if (request.getSession().getAttribute(attrName) != null) {
      return request.getSession().getAttribute(attrName).toString();
    }
    if (request.getAttribute(attrName) != null) {
      return request.getAttribute(attrName).toString();
    }
    return value;
  }
%>
<%
  String eventId = valueOrEmpty(request, "eventId");
  String userId = valueOrEmpty(request, "userId");
  String username = valueOrEmpty(request, "username");
  String timerId = valueOrEmpty(request, "timerId");
  String timerValue = valueOrEmpty(request, "timerValue");
  boolean isEditing = Boolean.parseBoolean(valueOrEmpty(request, "isEditing"));
%>
<body>
<% if (eventId.isEmpty()) {%>
<form action="Event" method="get">
  <input type="hidden" name="userId" value=<%=userId%>>
  <button class="btn btn-lg button-primary button-start" type="submit">
    <img class="button-icon" src="./icon-play.svg" alt="icon play"/>
    <span class="button-text">START</span>
  </button>
</form>
<%} else {%>
<form action="Event" method="post">
  <input type="hidden" name="userId" value=<%=userId%>>
  <input type="hidden" name="eventId" value=<%=eventId%>>
  <button class="btn btn-lg button-secondary button-stop" type="submit">
    <img class="button-icon" src="./icon-stop.svg" alt="icon stop"/>
    <span class="button-text">STOP</span>
  </button>
</form>
<%}%>

<% if (!isEditing) {%>
<div class="card">
  <form action="Timer" class="form-edit-timer" method="get">
    <h5 class="title">Time per task</h5>
    <h2>
      <%=timerValue%>
    </h2>
    <h6 class="non-header">seconds</h6>
    <input type="hidden" name="userId" value=<%=userId%>>
    <input type="hidden" name="username" value=<%=username%>>
    <input type="hidden" name="timerId" value=<%=timerId%>>
    <button class="btn button-small button-secondary button-edit" type="submit">
      <img class="button-icon" src="./icon-edit.svg" alt="icon edit"/>
      <span class="button-text">EDIT</span>
    </button>
  </form>
</div>
<%} else {%>
<div class="card">
  <form action="Timer" class="form-save-timer" method="post">
    <h5 class="title">Time per task</h5>
    <label>
      <input type="text" name="newTimerValue" value=<%=timerValue%>>
    </label>
    <h6 class="non-header">seconds</h6>
    <input type="hidden" name="userId" value=<%=userId%>>
    <input type="hidden" name="username" value=<%=username%>>
    <input type="hidden" name="timerId" value=<%=timerId%>>
    <button class="btn button-small button-primary button-save" type="submit">
      <img class="button-icon" src="./icon-save.svg" alt="icon save"/>
      <span class="button-text">SAVE</span>
    </button>
  </form>
</div>
<%}%>
</body>
</html>
<style type="text/css">
    .non-header {
        color: #000000;
    }

    h2 {
        margin-bottom: 0;
    }

    .card {
        padding: 8px;
        background: #FCFCFC;
        box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .25), 0 0 2px 0 rgba(0, 0, 0, .25);
        border: none;
        border-radius: 4px;
        width: 232px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
    }

    .button-small {
        margin-top: 8px;
        padding: 8px;
        width: 144px;
        height: 40px;
        border-radius: 4px;
        font-family: Roboto, sans-serif;
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 16px;
        letter-spacing: 1.25px;
        text-transform: uppercase;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }

    .btn-lg {
        margin-top: 40px;
        width: 232px;
        height: 232px;
        border-radius: 232px;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }

    .btn-lg > .button-icon {
        width: 40px;
        height: 40px;
    }

    .button-small > .button-icon {
        width: 24px;
        height: 24px;
        margin-right: 8px;
    }

    .button-stop {
        border-radius: 8px;
    }

    .btn-lg > .button-text {
        vertical-align: center;
        font-family: Roboto, sans-serif;
        font-weight: 500;
        font-size: 32px;
    }

    .button-primary > .button-icon {
        filter: invert(100%) sepia(100%) saturate(0%) hue-rotate(249deg) brightness(103%) contrast(103%);
    }

    .button-secondary > .button-text {
        color: #6200EE;
    }

    .button-secondary > .button-icon {
        filter: invert(9%) sepia(98%) saturate(5986%) hue-rotate(268deg) brightness(99%) contrast(122%);
    }

    .button-secondary:hover > .button-text {
        color: #FFFFFF;
        fill: #FFFFFF;
    }

    .button-secondary:hover > .button-icon {
        filter: invert(100%) sepia(100%) saturate(0%) hue-rotate(249deg) brightness(103%) contrast(103%);
    }
</style>