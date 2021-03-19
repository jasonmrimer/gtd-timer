package edu.louisville.cse640.rimer.gtd_timer;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import com.ibm.db2.jcc.*;

public class DB2Controller {
  public String dbName = null;
  public String userId = null;
  public String password = null;
  public String url = null;
  public String driver = null;
  public static Connection connection = null;

  public void initializeVariables() {
    driver = "com.ibm.db2.jcc.DB2Driver";
    dbName = "COMPANY";
    userId = "jmrime01";
    password = "Spring2021";
    url = "jdbc:db2://db2.cecsresearch.org:50000/COMPANY";
  }

  public User fetchUser(String username) throws SQLException {
    initializeVariables();
//    DB2Driver driver = new DB2Driver();
//    connection = driver.connect(url,dbName);
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      connection = DriverManager.getConnection(url, userId, password);
    } catch (SQLException sqlException) {
      System.err.println("Error with database connection");
      System.err.println(sqlException.getMessage());
      sqlException.printStackTrace();
    }
    Statement statement = connection.createStatement();
    System.out.println("==========checking for user: " + username);
    String query = "SELECT * FROM user WHERE username='" + username + "'";
    ResultSet resultSet = statement.executeQuery(query);
    User user = new User("no user found");
    while (resultSet.next()) {
      System.out.println(resultSet);
      System.out.println(resultSet.getString("username"));
      user = new User(resultSet.getString("username"));
    }
    disconnectFromDatabase();
    return user;
  }

  public void disconnectFromDatabase()
  {
    if (connection != null)
    {
      try
      {
        System.out.println("Disconnecting from database ...");
        connection.close();
        System.out.println("Disconnected from database.");
      }
      catch (Exception sqle)
      {
        System.out.println("Error closing connection");
        sqle.printStackTrace();
      }
    }
  }
}
