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

  public String startTimer(String userId) throws SQLException {
    connectToDatabase();
    Statement statement = connection.createStatement();

    String query = "select * from final table" +
      "(insert into EVENT " +
      "(USER_ID, START) " +
      "values (" + userId + ", current_timestamp ))";
    ResultSet resultSet = statement.executeQuery(query);

    String eventId = "";
    while (resultSet.next()) {
      eventId = resultSet.getString("id");
    }
    return eventId;
  }

  public void endTimer(String eventId) throws SQLException {
    connectToDatabase();
    Statement statement = connection.createStatement();

    String query = "update event " +
      "set END = current_timestamp " +
      "where id = " + eventId;
    statement.execute(query);
  }

  public User fetchUser(String username, String password) throws SQLException {
    connectToDatabase();
    Statement statement = connection.createStatement();
    String query = "SELECT * FROM user " +
      "WHERE username='" + username + "' " +
      "AND password='" + password + "'";
    ResultSet resultSet = statement.executeQuery(query);
    User user = null;
    while (resultSet.next()) {
      System.out.println(resultSet.getString("username"));
      System.out.println(resultSet.getString("id"));
      user = new User(
        Integer.parseInt(resultSet.getString("id")),
        resultSet.getString("username")
      );
    }
    disconnectFromDatabase();
    return user;
  }

  private void connectToDatabase() {
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
