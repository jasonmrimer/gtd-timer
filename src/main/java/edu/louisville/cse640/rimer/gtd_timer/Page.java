package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Page")
public class Page extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession().setAttribute("currentPage", "timer");
    req.getRequestDispatcher("/WEB-INF/timer.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession().setAttribute("currentPage", "analytics");
    req.getRequestDispatcher("/WEB-INF/analytics.jsp").forward(req, resp);
  }
}
