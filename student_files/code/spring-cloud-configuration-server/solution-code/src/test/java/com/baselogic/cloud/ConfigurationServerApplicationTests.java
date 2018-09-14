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

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class ConfigurationServerApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ConfigurationServerApplicationTests.class);

	// Note this is required for locally testing Spring Boot Web Applications
	// for Integration testing:
	@LocalServerPort
	private int port;
	
	private String host = "http://localhost";

	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	@Test
	public void test_getCloudDataFromServer(){
		String result = testRestTemplate.getForObject(
				createURLWithPort("/config-client/development"),
				String.class);
		
		log.info("*************************************************************************");
		log.info("Results: {}", result);
		
		String expected = "{\"name\":\"config-client\",\"profiles\":[\"development\"]";
		assertThat(result, containsString(expected));
		
	}
	
	private String createURLWithPort(String uri) {
		return host  +":"+ port + uri;
	}

} // The End...
