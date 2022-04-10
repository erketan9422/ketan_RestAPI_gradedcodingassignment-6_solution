package com.gl.employee.config;

/**
 * Simple Swagger configuration
 * can be accessed at '/swagger-ui.html' after '/login'
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket libraryApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Employee-API").select()
				.apis(RequestHandlerSelectors.basePackage("com.gl.employee.controller")).build();
	}
}
