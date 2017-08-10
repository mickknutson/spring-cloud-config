package com.baselogic.cloud.microservicesclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration for YML should be:
 * <pre>
 * config.client:
 *   message: Some Message
 *   credentials:
 *     provider: Eureka
 *     username: admin1
 *     password: changeme
 *     clients: 3
 * </pre>
 *
 * @author mickknutson
 */
@Component
@ConfigurationProperties(prefix="config.client")
public class MicroservicesClientProperties {

    private String message;
    private Credentials credentials;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Credentials getCredentials() {
        return credentials;
    }
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
	public String toString() {
		return "MicroservicesClientProperties [message=" + message + ", credentials=\n" + credentials + "]";
	}


}
