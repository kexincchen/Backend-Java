package com.example.adminsys;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
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

	// tag::customization-http-headers-providers[]
	@Bean
	public HttpHeadersProvider customHttpHeadersProvider() {
		return (instance) -> {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("X-CUSTOM", "My Custom Value");
			return httpHeaders;
		};
	}
}
