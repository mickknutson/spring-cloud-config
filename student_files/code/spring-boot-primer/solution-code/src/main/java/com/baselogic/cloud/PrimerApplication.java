package com.baselogic.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication
// TODO LAB: Disable Security AutoConfiguration
@EnableAutoConfiguration(
        exclude={SecurityAutoConfiguration.class}
)
public class PrimerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrimerApplication.class, args);
	}
	
} // The End...
