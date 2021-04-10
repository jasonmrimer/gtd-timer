package edu.louisville.cse640.rimer.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HistoryController {
  Connection connection;

  public HistoryController(Connection connection) {
    this.connection = connection;
  }

  public ArrayList<EventModel> fetchEventsForUser(String userId) {
    Statement statement;
    ArrayList<EventModel> events = new ArrayList<>();

    try {
      statement = connection.createStatement();

      String query = "select * from EVENT " +
        " where USER_ID = " + userId;
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        int id = Integer.parseInt(resultSet.getString("id"));
        LocalDateTime start = parseDate(resultSet, "start");
        LocalDateTime end = parseDate(resultSet, "end");
        int goal = Integer.parseInt(resultSet.getString("timer_seconds"));
        int elapsed = Math.toIntExact(start.until(end, ChronoUnit.SECONDS));
        events.add(new EventModel(id, start, goal, elapsed));
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return events;
  }

  LocalDateTime parseDate(ResultSet resultSet, String name) throws SQLException {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    String resultString = resultSet.getString(name);
    if (resultString == null) {
      return LocalDateTime.parse("2000-01-01 13:18:42.792", dateTimeFormatter);
    }

    while (resultString.length() < 25) {
      resultString += "0";
    }

    String fixedLengthString = resultString.substring(0, 23);
    return LocalDateTime
      .parse(
        fixedLengthString,
        dateTimeFormatter
      );
  }

  public void deleteEvent(String eventId) {
    Statement statement = null;

    try {
      statement = connection.createStatement();

      String query = "delete from EVENT " +
        " where ID = " + eventId;

      statement.execute(query);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }
}
