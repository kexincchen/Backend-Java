package com.example.adminsys;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminsysApplication.class, args);
	}

}
