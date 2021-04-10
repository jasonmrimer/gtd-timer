package edu.louisville.cse640.rimer.gtd_timer;

import edu.louisville.cse640.rimer.controllers.ConnectionPool;
import edu.louisville.cse640.rimer.controllers.TimerController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/Event")
public class Event extends HttpServlet {
  private static Connection connection = null;
  private String eventId;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    populateSharedAttributes(req, resp);
    if (connection != null) {
      TimerController timerController = new TimerController(connection);
      eventId = timerController.startTimer(
        req.getSession().getAttribute("userId").toString()
      );
    } else {
      System.err.println("Connection is null in Timer Servlet");
    }
    reloadTimerPage(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    populateSharedAttributes(req, resp);
    TimerController timerController = new TimerController(connection);
    if (connection != null) {
      timerController.endTimer(eventId);
      eventId = null;
    } else {
      System.err.println("Connection is null in Timer Servlet");
    }
    reloadTimerPage(req, resp);
  }

  private void populateSharedAttributes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    eventId = req.getParameter("eventId");

    ConnectionPool connectionPool = ConnectionPool.getInstance("jdbc/COMPANY");
    connection = connectionPool.getConnection();
  }

  private void reloadTimerPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("eventId", eventId);
    req
      .getRequestDispatcher("/WEB-INF/timer.jsp")
      .forward(req, resp);
  }

}

