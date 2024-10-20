package com.kushal.rs.auth.client.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableOAuth2Sso
@Configuration
public class OauthConfig {

  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    // IN below it means authorize is done for all the request except the root and
    // login page mentioned as below (/,/login**) and rest all the request needs to
    // be authenticated.
    return http.authorizeHttpRequests(authz -> authz.requestMatchers("/**").authenticated())
        .authorizeHttpRequests(
            authz ->
                authz.requestMatchers("/", "/login**").permitAll().anyRequest().authenticated())
        .build();
  }
}
