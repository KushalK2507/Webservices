package com.kushal.spring.security.jwt.handler;

import com.kushal.spring.security.jwt.domain.dto.ClientDetailsDto;
import com.kushal.spring.security.jwt.domain.mapper.ClientDetailsMapper;
import com.kushal.spring.security.jwt.model.ClientResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientHandler {

  private final UserDetailsService clientDetailsDomainService;
  private final ClientDetailsMapper clientDetailsMapper;

  public ClientResponse getClientDetails(String clientName) {
    var clientDetailsDto = clientDetailsDomainService.loadUserByUsername(clientName);
    if (clientDetailsDto != null) {
      return clientDetailsMapper.toClientResponse((ClientDetailsDto) clientDetailsDto);
    }
    return null;
  }
}
