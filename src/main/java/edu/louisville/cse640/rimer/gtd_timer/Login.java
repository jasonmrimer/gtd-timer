package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/Login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    try {
      User user = new DB2Controller().fetchUser(username, password);
      RequestDispatcher dispatcher;
      if (user != null) {
        req.setAttribute("username", user.username);
        dispatcher = req.getRequestDispatcher("/WEB-INF/home.jsp");
      } else {
        req.setAttribute("error", "Username or password incorrect");
        dispatcher = req.getRequestDispatcher("index.jsp");
      }
      dispatcher.forward(req, resp);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }
}
