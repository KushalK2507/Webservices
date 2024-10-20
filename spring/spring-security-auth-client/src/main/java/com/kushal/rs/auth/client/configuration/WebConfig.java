package com.kushal.rs.auth.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configure) {
    configure.enable();
  }

  // Below method is override to integrate and configure the UI part i.e. Tymeleaf
  // for now.
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {

    // Now to configure our own pages use below and forward it to index page
    registry.addViewController("/").setViewName("forward:/index");

    // In below we are telling the below 2 pages are secured i.e. user needs to be
    // logged in for accessing below pages
    registry.addViewController("/index");
    registry.addViewController("/secure");
  }

	// Below methods tells where would all resources present i.e. all the static web
	// page.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resource/**").addResourceLocations("/resoources/");
	}

	// Now we need to define the propertySourcePlaceHolder which will inject the
	// bean
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// Also need to request lister as we are creating the view Controller
	@Bean
	public RequestContextListener requestContextLister() {
		return new RequestContextListener();
	}
}
