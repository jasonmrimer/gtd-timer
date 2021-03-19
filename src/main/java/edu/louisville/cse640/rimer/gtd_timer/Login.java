package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("username", "not implemented");
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/home.jsp");
    dispatcher.forward(req, resp);
  }
}
