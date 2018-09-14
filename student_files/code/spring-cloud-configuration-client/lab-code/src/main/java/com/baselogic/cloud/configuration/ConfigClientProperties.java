package com.baselogic.cloud.configuration;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
 *
 */
@Component
@ConfigurationProperties(prefix = ConfigClientProperties.PREFIX, ignoreUnknownFields = true)
public class ConfigClientProperties {

	public static final String PREFIX = "config.client";

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
//	@Override
//	public String toString() {
//		return "ConfigClientProperties [message=" + message + ", credentials=" + credentials + "]";
//	}
	
	@Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

} // The End...
