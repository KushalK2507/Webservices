package org.kushal.reactive.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.kushal.reactive.domain.service.ClientDetailsDomainServiceImpl;
import org.kushal.reactive.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class JwtAuthFilter implements WebFilter {

  private final JwtService jwtUtils;

  @Autowired
  private ClientDetailsDomainServiceImpl clientDetailsDomainService;

  public JwtAuthFilter(
      ReactiveAuthenticationManager reactiveAuthenticationManager, JwtService jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain filterChain) {

    String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    if (authHeader != null && authHeader.startsWith("Bearer")) {
      String token = authHeader.substring(7);
      String username = authHeader;//jwtUtils.extractUserName(token);

      if (username != null) {
        return clientDetailsDomainService
            .findByUsername(username)
            .map(userDetails -> jwtUtils.validateToken(token, userDetails))
                .filter(res -> res)
                .map(res -> getUsernameAndPasswordAuthentication())
                .flatMap(authentication ->
                        filterChain.filter(exchange).contextWrite(ReactiveSecurityContextHolder
                .withAuthentication(authentication)))
                .switchIfEmpty(Mono.defer(() -> {
                  return filterChain.filter(exchange);
                }));
      }
    }
    return filterChain.filter(exchange);
  }
  public Authentication getUsernameAndPasswordAuthentication(){
    return new UsernamePasswordAuthenticationToken("Kushal", null,List.of());
  }
}
