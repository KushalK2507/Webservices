package com.kushal.spring.security.jwt.domain.mapper;

import com.kushal.spring.security.jwt.domain.dto.ClientDetailsDto;
import com.kushal.spring.security.jwt.domain.entity.ClientDetails;
import com.kushal.spring.security.jwt.model.ClientRequest;
import com.kushal.spring.security.jwt.model.ClientResponse;
import java.util.Arrays;
import lombok.AllArgsConstructor;
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
        .services(Arrays.stream(clientDetails.getServices().split(",")).toList());
    return builder.build();
  }

  public ClientDetails toClientEntity(ClientDetailsDto clientDetailsDto) {
    var builder = ClientDetails.builder();

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
