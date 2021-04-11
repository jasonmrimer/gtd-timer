package edu.louisville.cse640.rimer.controllers;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class HistoryControllerTest {

  @Test
  public void dateParser() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    LocalDateTime start = LocalDateTime
      .parse(
        "2021-04-03 19:20:33.217",
        dateTimeFormatter
      );
    LocalDateTime end = LocalDateTime
      .parse(
        "2021-04-03 19:20:36.217",
        dateTimeFormatter
      );

    assertEquals(
      3,
      Math.toIntExact(start.until(end, ChronoUnit.SECONDS))
    );
  }

}