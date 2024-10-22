package com.kushal.spring.security.jwt.handler;

import com.kushal.spring.security.jwt.model.ClientRequest;
import com.kushal.spring.security.jwt.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationHandler {

  private final UserDetailsService clientDetailsDomainService;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  public String generateToken(ClientRequest clientRequest) {
    var authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                clientRequest.getName(), clientRequest.getToken()));

    if (authentication.isAuthenticated()) {
      try {
        return jwtService.generateToken(clientRequest.getName());
      } catch (Exception e) {
        log.error("Error in Generating Token", e);
        return null;
      }
    }
    return null;
  }
}
