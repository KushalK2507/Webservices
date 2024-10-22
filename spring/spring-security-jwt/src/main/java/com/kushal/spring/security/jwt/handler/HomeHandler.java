package com.kushal.spring.security.jwt.handler;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HomeHandler {

  private final UserDetailsService clientDetailsDomainService;

  public Boolean isClientExist(String userName) {
    return clientDetailsDomainService.loadUserByUsername(userName) != null;
  }
}
