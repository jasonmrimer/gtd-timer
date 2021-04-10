package edu.louisville.cse640.rimer.gtd_timer;

import edu.louisville.cse640.rimer.controllers.ConnectionPool;
import edu.louisville.cse640.rimer.controllers.EventModel;
import edu.louisville.cse640.rimer.controllers.HistoryController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HistoryController historyController = getHistoryController();
    sendEvents(req, historyController);
  }

  private void sendEvents(HttpServletRequest req, HistoryController historyController) {
    String userId = req.getSession().getAttribute("userId").toString();
    ArrayList<EventModel> events = historyController.fetchEventsForUser(userId);
    req.setAttribute("events", events);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HistoryController historyController = getHistoryController();
    String eventId = req.getParameter("eventId");
    historyController.deleteEvent(eventId);
    sendEvents(req, historyController);
    req.getRequestDispatcher("WEB-INF/history.jsp").forward(req, resp);
  }

  private HistoryController getHistoryController() {
    ConnectionPool connectionPool = ConnectionPool.getInstance("jdbc/COMPANY");
    Connection connection = connectionPool.getConnection();
    return new HistoryController(connection);
  }
}
