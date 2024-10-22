package com.kushal.spring.security.jwt.controller;

import com.kushal.spring.security.jwt.handler.ClientHandler;
import com.kushal.spring.security.jwt.model.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

  @Autowired private ClientHandler clientHandler;

  @GetMapping("/{clientName}")
  public ResponseEntity<ClientResponse> getClientDetails(@PathVariable @NonNull String clientName) {

    var response = clientHandler.getClientDetails(clientName);
    if (response != null) {
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.notFound().build();
  }
}
