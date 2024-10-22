package com.kushal.spring.security.jwt.controller;

import com.kushal.spring.security.jwt.handler.HomeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

  @Autowired private HomeHandler homeHandler;

  @GetMapping("/welcome")
  public ResponseEntity<String> welcomePage() {
    log.info("Inside Welcome");
    return ResponseEntity.ok("Welcome");
  }

  @GetMapping("/{clientName}")
  public ResponseEntity<String> clientExist(@PathVariable @NonNull String clientName) {
    if (homeHandler.isClientExist(clientName)) {
      return ResponseEntity.ok("Client Exists");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Does Not Exist");
  }
}
