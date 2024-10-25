package com.kushal.spring.security.jwt.domain.entity;

import com.kushal.spring.security.jwt.domain.Role;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetails {

  @Id
  @Column(name = "CLIENT_ID")
  private String id;

  @Column(name = "CLIENT_NAME")
  private String clientName;

  @Column(name = "TOKEN")
  private String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "ROLE")
  private Role role;

  @Column(name = "ACCESS_SERVICES")
  private String services;
}
