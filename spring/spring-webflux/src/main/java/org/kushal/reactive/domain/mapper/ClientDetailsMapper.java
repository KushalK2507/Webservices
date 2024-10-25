package org.kushal.reactive.domain.mapper;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.kushal.reactive.domain.dto.ClientDetailsDto;
import org.kushal.reactive.domain.entity.ClientDetails;
import org.kushal.reactive.model.ClientRequest;
import org.kushal.reactive.model.ClientResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientDetailsMapper {

  private final PasswordEncoder passwordEncoder;

  public ClientDetailsDto toClientDetailsDtoFromRequest(ClientRequest clientRequest) {
    var builder = ClientDetailsDto.builder();
    builder
        .clientName(clientRequest.getName())
        .token(passwordEncoder.encode(clientRequest.getToken()))
        .services(clientRequest.getServices());
    return builder.build();
  }

  public ClientDetailsDto toClientDetailsDtoFromEntity(ClientDetails clientDetails) {
    var builder = ClientDetailsDto.builder();
    builder
        .clientName(clientDetails.getClientName())
        .role(clientDetails.getRole())
        .token(clientDetails.getToken())
        .services(Arrays.stream(clientDetails.getAccessServices().split(",")).toList());
    return builder.build();
  }

  public ClientDetails toClientEntity(ClientDetailsDto clientDetailsDto) {
    var builder = ClientDetails.builder();
    builder
        .clientName(clientDetailsDto.getClientName())
        .role(clientDetailsDto.getRole())
        .accessServices(String.join(",", clientDetailsDto.getServices()))
        .token(passwordEncoder.encode(clientDetailsDto.getToken()));
    return builder.build();
  }

  public ClientResponse toClientResponse(ClientDetailsDto clientDetailsDto) {
    var builder = ClientResponse.builder();
    builder
        .clientName(clientDetailsDto.getClientName())
        .accessServices(clientDetailsDto.getServices())
        .role(clientDetailsDto.getRole().name());

    return builder.build();
  }
}
