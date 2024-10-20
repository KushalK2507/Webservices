package com.kushal.rs.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceConfiguration {

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // To match the URLs and authenticate the URL.
    // and the login form is permitted to all.
    return http.authorizeHttpRequests(
            (authz) ->
                authz
                    .requestMatchers("/login", "/oauth/authorize")
                    .permitAll()
                    .anyRequest()
                    .authenticated()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    return new InMemoryUserDetailsManager(
        User.withDefaultPasswordEncoder()
            .username("Kushal")
            .password("kushal")
            .roles("ADMIN")
            .build());
  }
}
