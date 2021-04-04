package edu.louisville.cse640.rimer.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
  private Connection connection;

  public UserController(Connection connection) {
    this.connection = connection;
  }

  public User fetchUser(String username, String password) {
    User user = null;
    String query = "SELECT * FROM user " +
      "WHERE username='" + username + "' " +
      "AND password='" + password + "'";


    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        String userId = resultSet.getString("id");
        user = new User(
          Integer.parseInt(userId),
          resultSet.getString("username"),
          -1,
          -1
        );

        Statement statementForTimer = connection.createStatement();

        query = "select * " +
          "from TIMER " +
          "where USER_ID = " + userId;
        ResultSet resultSetForTimer = statementForTimer.executeQuery(query);

        while (resultSetForTimer.next()) {
          user.setTimerId(Integer.parseInt(resultSetForTimer.getString("id")));
          user.setTimerValue(Integer.parseInt(resultSetForTimer.getString("seconds")));
        }
      }

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return user;
  }
}
