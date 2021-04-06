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
  <button class="btn btn-lg button-primary button-start" type="submit">
    <img class="button-icon" src="../icon-play.svg" alt="icon play"/>
    <span class="button-text">START</span>
  </button>
</form>
<%}%>
<% if (!eventId.isEmpty()) {%>
<form action="Timer" method="post">
  <input type="hidden" name="userId" value=<%=userId%>>
  <input type="hidden" name="username" value=<%=username%>>
  <input type="hidden" name="eventId" value=<%=eventId%>>
  <button class="btn btn-lg button-secondary button-stop" type="submit">
    <img class="button-icon" src="../icon-stop.svg" alt="icon stop"/>
    <span class="button-text">STOP</span>
  </button>
</form>
<%}%>
<div class="card">
  <form action="EditTimer">
    <div><%=timerValue%>
    </div>
    <input type="text" name="newTimerValue">
    <input type="hidden" name="userId" value=<%=userId%>>
    <input type="hidden" name="username" value=<%=username%>>
    <input type="hidden" name="timerId" value=<%=timerId%>>
    <input type="submit" value="Edit">
  </form>
</div>
</body>
</html>
<style type="text/css">
    .button-start, .button-stop {
        margin-top: 40px;
        width: 232px;
        height: 232px;
        border-radius: 232px;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }

    .button-icon {
        width: 40px;
        height: 40px;
    }

    .button-stop:hover > .button-text, .button-icon {
        color: #FFFFFF;
        fill: #FFFFFF;
    }

    .button-text {
        vertical-align: center;
        font-family: Roboto, sans-serif;
        font-weight: 500;
        font-size: 32px;
    }

    .button-stop.button-text {
        color: #6200EE;
    }

    .button-stop.button-icon {
        filter: invert(9%) sepia(98%) saturate(5986%) hue-rotate(268deg) brightness(99%) contrast(122%);
    }

    .button-stop:hover > .button-icon {
        filter: invert(100%) sepia(100%) saturate(0%) hue-rotate(249deg) brightness(103%) contrast(103%);    }
</style>