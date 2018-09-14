package com.baselogic.cloud.service;

import com.baselogic.cloud.configuration.ConfigClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * View CLient Configuration
 * @RefreshScope is enabled, but in order to actually pick up new configuration
 * you must perform a GET request on the following URI:
 * http://localhost:8080/refresh
 * 
 * @author mickknutson
 *
 */

// LAB: Add Refresh scope and Rest Controller annotations:
@RefreshScope
@RestController
class ClientRestController {
	
	@Autowired
	private ConfigClientProperties configClientProperties;

    @Value("${user.role:noRole}")
    private String role;

    @Value("${message:NA}")
    private String message;

    @Value("${name:NA}")
    private String name;

    @Value("${default.only:NA}")
    private String defaultOnly;

    @Value("${development.profile.only:NA}")
    private String developmentProfileOnly;


    @RequestMapping("/")
    public String home() {
        return "Spring Boot Cloud Configuration Client";
    }

    /**
     * http://localhost:8080/validate
     * 
     * @return
     */
    @RequestMapping("/validate")
    public String validateConfiguration() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("/validate result: \n\n")
    	.append("'role': '").append(this.role).append("\n")
    	.append(", 'name': '").append(this.name).append("\n")
    	.append(", 'message': '").append(this.message).append("\n")
    	.append(", 'defaultOnly': '").append(this.defaultOnly).append("\n")
    	.append(", 'developmentProfileOnly': '").append(this.developmentProfileOnly).append("\n")
    	.append(", 'ConfigClientProperties': '").append(this.configClientProperties);
    	
    	return sb.toString();
    }

} // The End...