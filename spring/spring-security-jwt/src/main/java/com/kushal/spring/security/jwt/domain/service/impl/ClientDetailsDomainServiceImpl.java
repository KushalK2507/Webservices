package com.kushal.spring.security.jwt.domain.service.impl;

import com.kushal.spring.security.jwt.domain.mapper.ClientDetailsMapper;
import com.kushal.spring.security.jwt.domain.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsDomainServiceImpl implements UserDetailsService {

  @Autowired private ClientDetailsRepository clientDetailsRepository;

  @Autowired private ClientDetailsMapper clientDetailsMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return clientDetailsRepository
        .findByClientName(username)
        .map(clientDetailsMapper::toClientDetailsDtoFromEntity)
        .orElse(null);
  }
}
