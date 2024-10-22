package com.kushal.spring.security.jwt.domain.repository;

import com.kushal.spring.security.jwt.domain.entity.ClientDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, String> {
  Optional<ClientDetails> findByClientName(String clientName);
}
