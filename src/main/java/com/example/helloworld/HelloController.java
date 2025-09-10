package com.example.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
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
}
