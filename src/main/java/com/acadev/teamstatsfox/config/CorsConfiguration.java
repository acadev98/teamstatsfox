package com.acadev.teamstatsfox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				System.out.println("##########################################cambios aplicados");
				registry.addMapping("/**")
                .allowedOrigins("*") // Reemplaza "*" con los orígenes permitidos de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
//				registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE")
//				.allowedOrigins("http://149.50.138.137:3000");
			}
		};
	}
}
