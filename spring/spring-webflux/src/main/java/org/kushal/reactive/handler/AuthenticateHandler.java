package org.kushal.reactive.handler;

import lombok.extern.slf4j.Slf4j;
import org.kushal.reactive.domain.dto.ClientDetailsDto;
import org.kushal.reactive.domain.mapper.ClientDetailsMapper;
import org.kushal.reactive.domain.service.ClientDetailsDomainServiceImpl;
import org.kushal.reactive.exception.ClientNotFoundException;
import org.kushal.reactive.model.ClientRequest;
import org.kushal.reactive.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticateHandler {

  @Autowired private JwtService jwtService;

  @Autowired private ClientDetailsDomainServiceImpl clientDetailsDomainService;

  @Autowired private ClientDetailsMapper clientDetailsMapper;

  @Autowired private PasswordEncoder passwordEncoder;

  public Mono<ServerResponse> createClient(ServerRequest request) {

    return Mono.empty();
  }

  public Mono<ServerResponse> getClientDetails(ServerRequest request) {

      if ("Kushal".equals(request.pathVariable("clientName"))){
          return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue("Welcome "+request.pathVariable("clientName")));
      }
      return Mono.error(new ClientNotFoundException("Invalid Client Name"));
  }

  public Mono<ServerResponse> generateToken(ServerRequest request) {
    return request
        .bodyToMono(ClientRequest.class)
        .flatMap(this::validateUser)
        .map(clientRequest -> jwtService.generateToken(clientRequest.getName()))
        .flatMap(token -> ServerResponse.ok().body(BodyInserters.fromValue(token)))
        .onErrorResume(
            error -> {
              log.info("Error in Generating Token", error);
              return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                  .body(BodyInserters.fromValue("Client name does not exists"));
            })
            .switchIfEmpty(Mono.defer(() ->{ log.info("User does not exist");
            return      ServerResponse.status(HttpStatus.UNAUTHORIZED)
                    .body(BodyInserters.fromValue("Client name does not exists"));
            }));
  }

  public Mono<ClientRequest> validateUser(ClientRequest clientRequest) {
    return clientDetailsDomainService
        .findByUsername(clientRequest.getName())
        .doOnNext(
            request ->
                log.info("Password Encoded = {}", passwordEncoder.encode(request.getPassword())))
        .filter(
            clientDetails ->
                passwordEncoder.matches(
                    clientRequest.getToken(), ((ClientDetailsDto) clientDetails).getToken()))
        .map(result -> clientRequest)
        .switchIfEmpty(
            Mono.error(new ClientNotFoundException("ClientName and password does not match")));
  }
}
