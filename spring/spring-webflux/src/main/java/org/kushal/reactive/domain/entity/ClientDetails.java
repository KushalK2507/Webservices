package org.kushal.reactive.domain.entity;

import lombok.*;
import org.kushal.reactive.domain.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class ClientDetails {

  @Id public String clientId;

  private String clientName;

  private String accessServices;

  private String token;

  private Role role;
}
