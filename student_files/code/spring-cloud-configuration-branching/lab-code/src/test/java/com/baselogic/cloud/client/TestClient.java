package com.baselogic.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TestClient {

	private static final Logger log = LoggerFactory.getLogger(TestClient.class);

	private static String usersUri = "http://localhost:8888";

	/** 
	 * Simple Test method to verify the Spring Boot REST API.
	 * @param args
	 */
	public static void main(String args[]) {
		// LAB: Can input an optional application name
	    String application = (args != null && args.length > 0)? args[0] : "config-client";

		// LAB: Can input an optional active profile
	    String profile = (args != null && args.length > 0)? args[0] : "development";

		// LAB: Can input an optional branch
	    String branch = (args != null && args.length > 1)? args[1] : "master";
		
	    RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(usersUri + "/"+application+"/"+profile+"/"+branch, String.class);
		
		log.info("*** Result: {}", result);
		
		String expected = "{\"name\":\""+application+"\",\"profiles\":[\""+profile+"\"],\"label\":\""+branch+"\"";
		assertThat(result, containsString(expected));
		
		log.info("*** Successfully pulled configuration for ("+profile+"/"+branch+") through REST ***");

	}
}
