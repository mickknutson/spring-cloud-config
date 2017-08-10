package com.baselogic.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		// LAB: Can input an optional FirstName
	    String firstName = (args != null && args.length > 0)? args[0] : "Chuck";

		// LAB: Can input an optional LastName
	    String lastName = (args != null && args.length > 1)? args[1] : "Norris";
		
	    RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(usersUri + "/"+firstName+"/"+lastName, String.class);
		
		log.info("Result: {}", result);
		String expected = "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\"}";
		assertThat(result, containsString(expected));
		
		log.info("*** Successfully echoed a User ("+firstName+" "+lastName+") through REST ***");

	}
}
