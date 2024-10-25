package org.kushal.reactive.model;

import java.util.List;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

  private String name;
  private String token;
  private List<String> services;
}
