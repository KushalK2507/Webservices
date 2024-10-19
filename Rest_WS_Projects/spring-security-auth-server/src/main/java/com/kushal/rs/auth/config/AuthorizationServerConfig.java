package com.kushal.rs.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// Below we are allowing who all can access the autherization Key, for now we
		// are permitting all.
		// And for validation of this token we need to check token isAuthenticated or
		// not.
		// Below we have implemented the authorization for the Authentication we
		// implemented in Resource Server Configuration.
		security.tokenKeyAccess("premitAll()").checkTokenAccess("isAuthenticated()");
	}

	// Below configurer adapter is overrided which has client details.
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// In below we have created the im Memory access to ClientID and secret from
		// which client is accessing.
		// Below with AutoApprove we are just saying that token are generated.
		clients.inMemory().withClient("ClientID").secret("secret").authorizedGrantTypes("authorization_scopes")
				.scopes("user_info").autoApprove(true);
	}

	// Below is the endpoint override
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		// We need to override the endpoint with the authenticationManager which we created in Resource Configuration.
			endpoints.authenticationManager(authenticationManager);
	}

}
