package com.example.adminsys;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
@EnableAdminServer
@EnableDiscoveryClient
public class AdminsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminsysApplication.class, args);
	}

//	@Configuration
//	public static class SecurityPermitAllConfig {
//		@Bean
//		protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//			return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
//					.csrf(AbstractHttpConfigurer::disable).build();
//		}
//	}

//	@Bean
//	public HttpHeadersProvider customHttpHeadersProvider() {
//		return (instance) -> {
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.add("Username", "admin");
//			httpHeaders.add("Password", "admin");
//			return httpHeaders;
//		};
//	}
}
