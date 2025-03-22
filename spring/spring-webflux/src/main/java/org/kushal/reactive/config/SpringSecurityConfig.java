package org.kushal.reactive.config;

import org.kushal.reactive.filter.JwtAuthFilter;
import org.kushal.reactive.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig {

  @Autowired private JwtService jwtService;

  @Autowired private ReactiveUserDetailsService clientDetailsService;

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
    return httpSecurity
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(
            exchange ->
                exchange
                    .pathMatchers("/home/**", "/generateToken")
                    .permitAll()
                    .anyExchange()
                    .authenticated())
        .addFilterBefore(jwtAuthFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
        .build();
  }

  @Bean
  public ReactiveAuthenticationManager authenticationManger() {
    var userDetailsService =
        new UserDetailsRepositoryReactiveAuthenticationManager(clientDetailsService);
    userDetailsService.setPasswordEncoder(passwordEncoder());
    return userDetailsService;
  }

  @Bean
  JwtAuthFilter jwtAuthFilter() {
    return new JwtAuthFilter(authenticationManger(), jwtService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
