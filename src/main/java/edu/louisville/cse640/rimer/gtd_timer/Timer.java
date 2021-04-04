package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/Timer")
public class Timer extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String userId = req.getParameter("userId");
    String username = req.getParameter("username");

    try {
      String eventId = new DB2Controller().postTimer(userId);
      req.setAttribute("eventId", eventId);
      req.setAttribute("userId", userId);
      req.setAttribute("username", username);
      req
        .getRequestDispatcher("/WEB-INF/timer.jsp")
        .forward(req, resp);
    } catch (SQLException | ServletException | IOException exception) {
      exception.printStackTrace();
    }
  }
}

