package com.baselogic.cloud.microservicesclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
public class ServiceEndpoint {

    @Autowired
    private MicroservicesClientProperties microservicesClientProperties;

    @Value("${name:NA}")
    private String name;

    @Value("${message:NA}")
    private String message;

    @GetMapping("/")
    public String listEndpointProperties(){
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(name).append("\n");
        sb.append("Message: ").append(message).append("\n\n");

        sb.append("Client Properties: ").append(microservicesClientProperties).append("\n");

        return sb.toString();
    }
}
