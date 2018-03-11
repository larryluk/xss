package com.larryluk.xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class XssApplication {

	public static void main(String[] args) {
		SpringApplication.run(XssApplication.class, args);
	}
}
