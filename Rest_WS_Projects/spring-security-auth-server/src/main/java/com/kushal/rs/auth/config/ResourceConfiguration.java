package com.kushal.rs.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// To match the URLs and authenticate the URL.
		// and the login form is permitted to all.
		http.requestMatchers().antMatchers("/login", "/oauth/authorize").and().authorizeRequests().anyRequest()
				.authenticated().and().formLogin().permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Below is used to authentication Manager which we currently implementating
		// only for in Memory usage, and set the User and password with roles as ADMIN.

		//Below we are implementating the Authentication below and do the Authorization in AuthorizationServerConfig
		auth.parentAuthenticationManager(authenticationManager).inMemoryAuthentication().withUser("Kushal").password("kushal").roles("ADMIN");

	}

}
