package com.example.adminsys;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminsysApplication.class, args);
	}

	@Configuration
	public static class SecurityPermitAllConfig {
		@Bean
		protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
					.csrf(AbstractHttpConfigurer::disable).build();
		}
	}
}
