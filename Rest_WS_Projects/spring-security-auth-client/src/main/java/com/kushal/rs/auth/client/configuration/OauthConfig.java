package com.kushal.rs.auth.client.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
// WebSecurityConfigurerAdapter helps to enable the Spring  security and overrides method below method to customize the Spring Security
public class OauthConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// IN below it means authorize is done for all the request except the root and
		// login page mentioned as below (/,/login**) and rest all the request needs to
		// be authenticated.
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**").permitAll().anyRequest()
				.authenticated();
	}

}
