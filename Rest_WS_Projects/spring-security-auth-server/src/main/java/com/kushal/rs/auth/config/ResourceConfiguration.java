package com.kushal.rs.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableResourceServer
@Configuration
public class ResourceConfiguration {

  @Autowired AuthenticationManager authenticationManager;

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
                    .authenticated())
        .formLogin(Customizer.withDefaults())
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
