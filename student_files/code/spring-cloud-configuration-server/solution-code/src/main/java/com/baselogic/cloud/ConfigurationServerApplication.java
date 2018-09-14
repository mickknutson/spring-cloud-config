package com.baselogic.cloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServerApplication.class, args);
	}

	// TODO: Find out how to use debug=true property to control this:
	// @Profile("trace")
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			String[] beanNames = ctx.getBeanDefinitionNames();

			System.out.println("\n********************************************");
			System.out.println("Listing the " + beanNames.length + " beans loaded by Spring Boot:");
			
			Arrays.stream(beanNames).sorted().forEach(System.out::println);
			
			System.out.println("*** the End ********************************\n");
		};
	}

	@GetMapping(value = "/")
	public String defaultEndpoint() {
		return "ConfigurationServerApplication active";
	}

} // The End
