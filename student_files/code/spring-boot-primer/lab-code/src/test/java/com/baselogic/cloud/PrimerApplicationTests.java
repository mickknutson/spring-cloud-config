package com.baselogic.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Simple Integration Test for Spring Boot REST service
 * 
 * @see {@link TestClient}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class PrimerApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(PrimerApplicationTests.class);

	// Note this is required for locally testing Spring Boot Web Applications
	// for Integration testing:
	@LocalServerPort
	private int port;
	
	private String host = "http://localhost";

	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	@Test
	public void test_EchoUser() {
	    String firstName = "Chuck";
	    String lastName = "Norris";
		
		String result = testRestTemplate.getForObject(
				createURLWithPort("/"+firstName+"/"+lastName), 
				String.class);
		
		log.info("Result: {}", result);
		
		String expected = "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\"}";
		assertThat(result, containsString(expected));
		
		log.info("*** Successfully echoed a User ("+firstName+" "+lastName+") through REST ***");

	}

	private String createURLWithPort(String uri) {
		return host  +":"+ port + uri;
	}


} // The End...
