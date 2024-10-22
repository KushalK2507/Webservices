package com.kushal.spring.security.jwt.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientRequest {

  private String name;
  private String token;
  private List<String> services;
}
