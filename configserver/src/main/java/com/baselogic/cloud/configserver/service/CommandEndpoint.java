package com.baselogic.cloud.configserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommandEndpoint {

    public String getGreeting(String username) {
        return new RestTemplate()
                .getForObject("http://localhost:9090/greeting/{username}",
                        String.class, username);
    }

    private String defaultGreeting(String username) {
        return "Hello User!";
    }

}
