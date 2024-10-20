package com.kushal.springdoc.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigSpringDoc {

	@Bean
	public GroupedOpenApi productApi() {
		String[] paths = {"/hello"};
    return GroupedOpenApi.builder()
        .group("welcome")
        .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Hello")))
			.pathsToMatch(paths)
			.packagesToScan("com.kushal.springdoc")
        .build();
	}

}
