package com.kushal.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigSwagger {

	@Bean
	public Docket productApi() {
		// Below SWAGGER 2 specifies we are using Swagger 2.0 version.
		// Request handler is use to select the base package of controller for which all
		// the API will be covered in Documentation
		// The Path selector is used map all the API which are exposed.
		// At last build method is called to build all these and return the Docket class
		// object
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.kushal.swagger.controller"))
				.paths(PathSelectors.regex("/hello.*")).build();
	}

}
