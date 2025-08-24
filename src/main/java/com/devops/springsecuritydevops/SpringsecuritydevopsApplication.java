package com.devops.springsecuritydevops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringsecuritydevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecuritydevopsApplication.class, args);
	}

}
