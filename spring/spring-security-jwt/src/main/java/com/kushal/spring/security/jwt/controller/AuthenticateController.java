package com.kushal.spring.security.jwt.controller;

import com.kushal.spring.security.jwt.handler.AuthenticationHandler;
import com.kushal.spring.security.jwt.model.ClientRequest;
import com.kushal.spring.security.jwt.model.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

  @Autowired private AuthenticationHandler authenticationHandler;

  @PostMapping("/generateToken")
  public ResponseEntity<?> generateToken(@RequestBody @NonNull ClientRequest clientDetails) {
    var token = authenticationHandler.generateToken(clientDetails);
    if (token != null) {
      return ResponseEntity.ok(new JwtToken(token));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
  }
}
