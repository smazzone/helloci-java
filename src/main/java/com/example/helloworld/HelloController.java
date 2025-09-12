package com.example.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  public static final String MESSAGE = "Hello, Harness CI on Cloud!";

  @GetMapping("/")
  public String hello() {
    return MESSAGE;
  }

  @GetMapping("/hello")
  public String helloWorld() {
    return MESSAGE;
  }

  // 🚨 Intentional Insecure Example for SAST Testing Only
  // DO NOT USE IN PRODUCTION
  @GetMapping("/insecure")
  public String insecure(@RequestParam String input) {
    // Insecure: directly concatenating user input into SQL
    String query = "SELECT * FROM users WHERE name = '" + input + "'";
    return query; // Just returning query for demonstration
  }

}
