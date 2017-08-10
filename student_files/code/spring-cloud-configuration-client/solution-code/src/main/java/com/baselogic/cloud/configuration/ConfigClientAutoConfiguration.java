package com.baselogic.cloud.configuration;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link ConfigClientProperties}s.
 * @author mickknutson
 *
 */
//@Configuration
//@EnableConfigurationProperties({ ConfigClientProperties.class })
public class ConfigClientAutoConfiguration {

	private final ConfigClientProperties properties;

	public ConfigClientAutoConfiguration(ConfigClientProperties properties) {
		this.properties = properties;
	}

	@PostConstruct
	private void init() {
		System.out.println("\n********************************************");
		System.out.println("Spring Boot - @ConfigurationProperties loaded:");
		System.out.println(properties.toString());
		System.out.println("\n********************************************\n");
	}


} // The End...
