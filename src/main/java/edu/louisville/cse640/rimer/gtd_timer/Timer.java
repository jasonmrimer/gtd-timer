package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet(value = "/Timer")
public class Timer extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String userId = req.getParameter("userId");
    LocalDateTime start = LocalDateTime.now();

//      start = new SimpleDateFormat("dd/MM/yyy").parse(req.getParameter("start"));
    Timestamp timestamp = Timestamp.valueOf(start);
    System.out.println("========================");
    System.out.println(userId + " at" + timestamp);
    try {
      String eventId = new DB2Controller().postTimer(userId, start);
      System.out.println("=========== did it: " + eventId);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }
}

