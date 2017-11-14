package com.baselogic.cloud.configserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Allow AJAX preflight requests via HttpMethod.OPTIONS to be made without
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }

} // The End...
