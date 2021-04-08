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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/Login")
public class Login extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    ConnectionPool connectionPool = ConnectionPool.getInstance("jdbc/COMPANY");
    Connection connection = connectionPool.getConnection();

    UserController userController = new UserController(connection);
    User user = userController.fetchUser(username, password);

    if (connection != null) {
      RequestDispatcher dispatcher;

      if (user != null) {
        hydrateSession(session, user);
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

  private void hydrateSession(HttpSession session, User user) {
    session.setAttribute("userId", user.getId());
    session.setAttribute("username", user.getUsername());
    session.setAttribute("timerId", user.getTimerId());
    session.setAttribute("timerValue", user.getTimerValue());
  }
}
