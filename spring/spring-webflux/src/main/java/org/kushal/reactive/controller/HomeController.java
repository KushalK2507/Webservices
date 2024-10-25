package org.kushal.reactive.controller;

import lombok.AllArgsConstructor;
import org.kushal.reactive.handler.HomeHandler;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

  private final HomeHandler homeHandler;

  @GetMapping("/welcome")
  public Mono<String> welcome() {
    return Mono.just("Welcome to reactive world");
  }

  @GetMapping("/{clientName}")
  public Mono<ServerResponse> clientExists(@PathVariable @NonNull String clientName) {
    return homeHandler.isClientExists(clientName);
  }
}
