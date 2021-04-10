package edu.louisville.cse640.rimer.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventModel {
  int id;
  LocalDateTime dateTime;
  int goal;
  int elapsed;
}
