package org.kushal.reactive.domain.service;

import lombok.AllArgsConstructor;
import org.kushal.reactive.domain.mapper.ClientDetailsMapper;
import org.kushal.reactive.domain.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ClientDetailsDomainServiceImpl implements ReactiveUserDetailsService {

  @Autowired private final ClientDetailsRepository clientDetailsRepository;

  @Autowired private final ClientDetailsMapper clientDetailsMapper;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return clientDetailsRepository
        .findByClientName(username)
        .map(clientDetailsMapper::toClientDetailsDtoFromEntity);
  }
}
