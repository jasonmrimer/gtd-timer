package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/SignUp")
public class SignUp extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    RequestDispatcher dispatcher;
    dispatcher = req.getRequestDispatcher("/WEB-INF/signup.jsp");
    try {
      dispatcher.forward(req, resp);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }
}
