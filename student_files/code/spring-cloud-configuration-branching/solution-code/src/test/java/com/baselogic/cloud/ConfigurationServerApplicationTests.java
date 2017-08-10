package com.baselogic.cloud;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;

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

	private TestRestTemplate testRestTemplate = new TestRestTemplate("user1", "s3cr3t");
	
	@Test
	public void test_getMasterBranchFromServer(){
		String result = testRestTemplate.getForObject(
				createURLWithPort("/config-client/development/master"),
				String.class);
		
		log.info("*************************************************************************");
		log.info("test_getMasterBranchFromServer Results: {}", result);
		
		String expected = "{\"name\":\"config-client\",\"profiles\":[\"development\"],\"label\":\"master\"";
		assertThat(result, containsString(expected));

		log.info("*** Successfully pulled configuration for (development/master) through REST ***");

	}
	
	@Test
	public void test_getNewBranchFromServer(){
		String result = testRestTemplate.getForObject(
				createURLWithPort("/config-client/development/new_branch"),
				String.class);
		
		log.info("*************************************************************************");
		log.info("test_getNewBranchFromServer Results: {}", result);
		
		String expected = "{\"name\":\"config-client\",\"profiles\":[\"development\"],\"label\":\"new_branch\"";
		assertThat(result, containsString(expected));

		log.info("*** Successfully pulled configuration for (development/master) through REST ***");

	}
	
	@Test
	public void test_getSpecificConfigFileFromServer(){
		String result = testRestTemplate.getForObject(
				createURLWithPort("/config-client/development/new_branch/branch_only_properties.yml"),
				String.class);
		
		log.info("*************************************************************************");
		log.info("test_getSpecificConfigFileFromServer Results: {}", result);
		
		String expected = "message: No other branch can see this message";
		assertThat(result, containsString(expected));
	}
	
	private String createURLWithPort(String uri) {
		return host +":"+ port + uri;
	}

} // The End...
