<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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