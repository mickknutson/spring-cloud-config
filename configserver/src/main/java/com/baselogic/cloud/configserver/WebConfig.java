package com.baselogic.cloud.configserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Another way to enable CORS.
 */
//@Configuration
public class WebConfig //extends WebMvcConfigurerAdapter
{

//    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                .allowedHeaders("Origin","X-Requested-With",
                        "Content-Type","Accept","X-Auth-Token","X-Csrf-Token",
                        "WWW-Authenticate","Authorization")
                .exposedHeaders("header1", "header2")
                .allowCredentials(false).maxAge(3600);
    }

} // The End...
