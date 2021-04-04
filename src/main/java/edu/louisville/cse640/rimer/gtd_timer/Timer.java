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

  private String userId;
  private String username;
  private String eventId;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    populateSharedAttributes(req, resp);
    try {
      eventId = new DB2Controller().startTimer(userId);
    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    reloadTimerPage(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    populateSharedAttributes(req, resp);

    try {
      new DB2Controller().endTimer(eventId);
      eventId = null;
    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    reloadTimerPage(req, resp);
  }

  private void populateSharedAttributes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    userId = req.getParameter("userId");
    username = req.getParameter("username");
    eventId = req.getParameter("eventId");
  }

  private void reloadTimerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("eventId", eventId);
    req.setAttribute("userId", userId);
    req.setAttribute("username", username);
    req
      .getRequestDispatcher("/WEB-INF/timer.jsp")
      .forward(req, resp);
  }
}

