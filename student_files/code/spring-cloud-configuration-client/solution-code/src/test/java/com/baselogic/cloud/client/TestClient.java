package com.baselogic.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * The following are some variations to pull valid configuration:
 * Without authentication:
 * <pre>
 * curl http://localhost:8888/config-client/development/
 * curl http://localhost:8888/config-client/development/new_branch/
 * curl http://localhost:8888/config-client/development/v.1.0
 * curl http://localhost:8888/config-client/development/v.2.0
 * curl http://localhost:8888/config-client/v.2.0
 * </pre>
 * 
 * With BASIC authentication:
 * <pre>
 * curl http://user1:s3cr3t@localhost:8888/config-client/development/
 * curl http://user1:s3cr3t@localhost:8888/config-client/development/new_branch/
 * curl http://user1:s3cr3t@localhost:8888/config-client/development/v.1.0
 * curl http://user1:s3cr3t@localhost:8888/config-client/development/v.2.0
 * curl http://user1:s3cr3t@localhost:8888/config-client/v.2.0
 * </pre>
 * 
 * @author mickknutson
 * 
 * TODO: Need to enable @ConfigurationProperties
 *
 */
public class TestClient {

	private static final Logger log = LoggerFactory.getLogger(TestClient.class);

	private static String uri = "http://localhost:8888/config-client/development/";

	/**
	 * Simple Test method to verify the Spring Boot REST API.
	 * @param args
	 */
	public static void main(String args[]) {

	    // We need to use a custom RestTemplate that will allow us to send BASIC Authentication
        // Credentials:
		TestRestTemplate restTemplate = new TestRestTemplate("user1", "s3cr3t");

		// First get user, and there should be none.
		String result = restTemplate.getForObject(uri, String.class);
		log.info("getForObject.uri: {}", uri);
		log.info("getForObject.result: {}", result);
		assertThat(result, containsString("{\"name\":\"config-client\""));
		
		log.info("*** Successfully pulled properties through REST ***");

	}
}
