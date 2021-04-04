package edu.louisville.cse640.rimer.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
  int id;
  String username;
  int timerId;
  int timerValue;
}
