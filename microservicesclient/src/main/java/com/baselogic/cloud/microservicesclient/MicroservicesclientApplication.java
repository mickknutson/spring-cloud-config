package com.baselogic.cloud.microservicesclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ MicroservicesClientProperties.class })
public class MicroservicesclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesclientApplication.class, args);
	}
}
