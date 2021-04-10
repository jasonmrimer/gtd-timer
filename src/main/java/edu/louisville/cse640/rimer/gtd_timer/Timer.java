package edu.louisville.cse640.rimer.gtd_timer;

import edu.louisville.cse640.rimer.controllers.ConnectionPool;
import edu.louisville.cse640.rimer.controllers.TimerController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/Timer")
public class Timer extends HttpServlet {
  private static Connection connection = null;

  private String userId;
  private String username;
  private String eventId;
  private String timerId;
  private String newTimerValue;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    extractParameters(req, resp);
    req.setAttribute("isEditing", true);
    reloadTimerPage(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    extractParameters(req, resp);
    connect();

    if (connection != null) {
      TimerController timerController = new TimerController(connection);
      eventId = timerController.editTimer(
        timerId,
        newTimerValue
      );
    } else {
      System.err.println("Connection is null in Timer Servlet");
    }

    req.setAttribute("isEditing", false);
    reloadTimerPage(req, resp);
  }

  private void extractParameters(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    userId = session.getAttribute("userId").toString();
    username = session.getAttribute("username").toString();
    timerId = session.getAttribute("timerId").toString();
    eventId = req.getAttribute("eventId").toString();
    newTimerValue = req.getParameter("newTimerValue");
  }

  private void connect() {
    ConnectionPool connectionPool = ConnectionPool.getInstance("jdbc/COMPANY");
    connection = connectionPool.getConnection();
  }

  private void reloadTimerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("eventId", eventId);
    req.setAttribute("userId", userId);
    req.setAttribute("username", username);
    req.setAttribute("timerId", timerId);
    req.setAttribute("timerValue", newTimerValue);

    req
      .getRequestDispatcher("/WEB-INF/timer.jsp")
      .forward(req, resp);
  }
}
