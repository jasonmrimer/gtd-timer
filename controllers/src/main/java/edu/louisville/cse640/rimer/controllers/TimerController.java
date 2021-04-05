package edu.louisville.cse640.rimer.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TimerController {
  private final Connection connection;

  public TimerController(Connection connection) {
    this.connection = connection;
  }

  public String startTimer(String userId) {
    String eventId = "";
    Statement statement = null;

    try {
      statement = connection.createStatement();

      String query = "select * from final table" +
        "(insert into EVENT " +
        "(USER_ID, START) " +
        "values (" + userId + ", current_timestamp ))";
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        eventId = resultSet.getString("id");
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return eventId;
  }

  public void endTimer(String eventId) {
    Statement statement;

    try {
      statement = connection.createStatement();

      String query = "update event " +
        "set END = current_timestamp " +
        "where id = " + eventId;
      statement.execute(query);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  public String editTimer(String timerId, String newTimerValue) {
    Statement statement;

    try {
      statement = connection.createStatement();

      String query = "update TIMER " +
        "set SECONDS = " + newTimerValue +
        " where id = " + timerId;
      statement.execute(query);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return newTimerValue;
  }
}
