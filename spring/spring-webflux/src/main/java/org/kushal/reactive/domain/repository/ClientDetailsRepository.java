package org.kushal.reactive.domain.repository;

import org.kushal.reactive.domain.entity.ClientDetails;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientDetailsRepository extends ReactiveCrudRepository<ClientDetails, String> {

  Mono<ClientDetails> findByClientName(String clientName);
}
