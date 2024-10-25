package org.kushal.reactive.filter;

import java.util.ArrayList;
import org.kushal.reactive.domain.service.ClientDetailsDomainServiceImpl;
import org.kushal.reactive.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class JwtAuthFilter extends AuthenticationWebFilter {

  private final JwtService jwtUtils;

  private ClientDetailsDomainServiceImpl clientDetailsDomainService;

  public JwtAuthFilter(
      ReactiveAuthenticationManager reactiveAuthenticationManager, JwtService jwtUtils) {
    super(reactiveAuthenticationManager);
    this.jwtUtils = jwtUtils;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain filterChain) {

    String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    if (authHeader != null && authHeader.startsWith("Bearer")) {
      String token = authHeader.substring(7);
      String username = jwtUtils.extractUserName(token);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        clientDetailsDomainService
            .findByUsername(username)
            .map(userDetails -> jwtUtils.validateToken(token, userDetails))
            .doOnNext(
                validationResult -> {
                  if (Boolean.TRUE.equals(validationResult)) {
                    UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                  }
                })
            .then();
      }
    }
    return filterChain.filter(exchange);
  }
}
