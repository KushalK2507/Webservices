package test.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import test.handler.WelcomeHandler;

@Component
public class WelcomeRouter {

  private final WelcomeHandler welcomeHandler;

  public WelcomeRouter(WelcomeHandler welcomeHandler) {
    this.welcomeHandler = welcomeHandler;
  }

  @Bean
  public RouterFunction<ServerResponse> welcome() {
    return RouterFunctions.route()
        .GET(path("v1/welcome"), welcomeHandler::welcome)
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> welcomeUser() {
    return RouterFunctions.route()
        .GET(path("v1/welcome/{userName}"), welcomeHandler::welcomeUser)
        .filter(this::validate)
        .build();
  }

  public Mono<ServerResponse> validate(
      ServerRequest request, HandlerFunction<ServerResponse> handlerFunction) {
    if (request.pathVariable("userName").equals("Kushal")) {
      return handlerFunction.handle(request);
    }
    return ServerResponse.status(HttpStatus.UNAUTHORIZED)
        .body(BodyInserters.fromValue("unauthorized user " + request.pathVariable("userName")));
  }
}
