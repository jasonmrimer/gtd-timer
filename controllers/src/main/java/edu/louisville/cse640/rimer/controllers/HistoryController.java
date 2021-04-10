package edu.louisville.cse640.rimer.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HistoryController {
  Connection connection;

  public HistoryController(Connection connection) {
    this.connection = connection;
  }

  public ArrayList<EventModel> fetchEventsForUser(String userId) {
    Statement statement = null;
    ArrayList<EventModel> events = new ArrayList<>();

    try {
      statement = connection.createStatement();

      String query = "select * from EVENT " +
        " where USER = " + userId;
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        LocalDateTime start = LocalDateTime.parse(resultSet.getString("start"));
        LocalDateTime end = LocalDateTime.parse(resultSet.getString("end"));
        int goal = Integer.parseInt(resultSet.getString("timer_seconds"));
        int elapsed = Math.toIntExact(start.until(end, ChronoUnit.SECONDS));
        events.add(new EventModel(
          start,
          goal,
          elapsed
        ));
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return events;
  }
}
