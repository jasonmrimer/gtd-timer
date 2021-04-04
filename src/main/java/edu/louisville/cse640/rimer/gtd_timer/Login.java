package edu.louisville.cse640.rimer.gtd_timer;

import edu.louisville.cse640.rimer.controllers.ConnectionPool;
import edu.louisville.cse640.rimer.controllers.User;
import edu.louisville.cse640.rimer.controllers.UserController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/Login")
public class Login extends HttpServlet {
  private static Connection connection = null;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    ConnectionPool connectionPool = ConnectionPool.getInstance("jdbc/COMPANY");
    connection = connectionPool.getConnection();

    UserController userController = new UserController(connection);
    User user = userController.fetchUser(username, password);

    if (connection != null) {
      RequestDispatcher dispatcher;

      if (user != null) {
        req.setAttribute("userId", user.getId());
        req.setAttribute("username", user.getUsername());
        req.setAttribute("timer", user.getTimerValue());
        dispatcher = req.getRequestDispatcher("/WEB-INF/timer.jsp");
      } else {
        req.setAttribute("error", "Username or password incorrect");
        dispatcher = req.getRequestDispatcher("index.jsp");
      }
      connectionPool.freeConnection(connection);
      dispatcher.forward(req, resp);
    } else {
      System.err.println("Connection is null");
    }
  }
}
