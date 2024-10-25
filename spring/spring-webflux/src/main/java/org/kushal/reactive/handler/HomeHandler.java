package org.kushal.reactive.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HomeHandler {

  public Mono<ServerResponse> isClientExists(String clientName) {

    return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("Client Does Not Exist");
  }
}
