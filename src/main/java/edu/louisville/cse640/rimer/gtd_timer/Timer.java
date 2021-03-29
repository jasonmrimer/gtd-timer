package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@WebServlet(value = "/Timer")
public class Timer extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    String username = req.getParameter("username");
    LocalDate start = LocalDate.now();

//      start = new SimpleDateFormat("dd/MM/yyy").parse(req.getParameter("start"));

    System.out.println("========================");
    System.out.println(username + start);

  }
}

