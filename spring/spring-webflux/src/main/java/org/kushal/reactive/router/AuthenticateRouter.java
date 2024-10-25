package org.kushal.reactive.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kushal.reactive.handler.AuthenticateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@Slf4j
@AllArgsConstructor
public class AuthenticateRouter {

  private final AuthenticateHandler authenticateHandler;

  @Bean
  public RouterFunction<ServerResponse> authentication() {
    return RouterFunctions.route()
        .POST("/generateToken", authenticateHandler::generateToken)
        .nest(
            path("/authenticate"),
            builder ->
              builder
                  .POST("/createClient", authenticateHandler::createClient)
                  .GET("/getClientDetails/{clientName}", authenticateHandler::getClientDetails))
        .build();
  }
}
