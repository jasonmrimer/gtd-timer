package edu.louisville.cse640.rimer.gtd_timer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
  private Connection connection = null;

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
        user = new User(
          Integer.parseInt(resultSet.getString("id")),
          resultSet.getString("username")
        );
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return user;
  }
}
