package edu.louisville.cse640.rimer.gtd_timer;

import java.sql.*;

public class DB2Controller {
  public String dbName = null;
  public String userId = null;
  public String dbPassword = null;
  public String url = null;
  public String driver = null;
  public static Connection connection = null;

  public void initializeVariables() {
    driver = "com.ibm.db2.jcc.DB2Driver";
    dbName = "COMPANY";
    userId = "jmrime01";
    dbPassword = "Spring2021";
    url = "jdbc:db2://db2.cecsresearch.org:50000/COMPANY";
  }

  public User fetchUser(String username, String password) throws SQLException {
    initializeVariables();
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      connection = DriverManager.getConnection(url, userId, dbPassword);
    } catch (SQLException sqlException) {
      System.err.println("Error with database connection");
      System.err.println(sqlException.getMessage());
      sqlException.printStackTrace();
    }
    Statement statement = connection.createStatement();
    String query = "SELECT * FROM user " +
      "WHERE username='" + username + "' " +
      "AND password='" + password + "'";
    ResultSet resultSet = statement.executeQuery(query);
    User user = null;
    while (resultSet.next()) {
      System.out.println(resultSet);
      System.out.println(resultSet.getString("username"));
      user = new User(resultSet.getString("username"));
    }
    disconnectFromDatabase();
    return user;
  }

  public void disconnectFromDatabase() {
    if (connection != null) {
      try {
        System.out.println("Disconnecting from database ...");
        connection.close();
        System.out.println("Disconnected from database.");
      } catch (Exception sqle) {
        System.out.println("Error closing connection");
        sqle.printStackTrace();
      }
    }
  }
}
