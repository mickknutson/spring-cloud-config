package com.baselogic.cloud.configserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * To activate profile in bootRun:
 *
 * Gradle:
 * gradle bootRun -Dspring.profiles.active=corsSecurityConfig
 *
 * Maven
 * mvn spring-boot:run -Drun.profiles=corsSecurityConfig
 */
@Configuration
@Profile("corsSecurityConfig")
public class CORSSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Enable the default CORSFilter:
        http.cors();
    }

} // The End...
