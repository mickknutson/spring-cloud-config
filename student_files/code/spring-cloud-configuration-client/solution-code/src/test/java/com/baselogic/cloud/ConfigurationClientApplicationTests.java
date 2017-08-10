package com.baselogic.cloud;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class ConfigurationClientApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ConfigurationClientApplicationTests.class);

	// This is the CLIENT port, not the SERVER port:
	private int port = 8080;
	
	private String host = "http://localhost";

	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	@Test
	public void test_validateClientConfiguration(){
		String result = testRestTemplate.getForObject(
				createURLWithPort("validate"),
				String.class);
		
		log.info("*************************************************************************");
		log.info("test_validateClientConfiguration Results: {}", result);
		
		// LAB: "master" branch result:
		//String expected = "/validate result: 'role': 'YAML Developer', 'name': 'Tony Stark', 'message': 'Welcome to YAML developer properties'";

		// LAB: "master" branch result:
		//String expected = "/validate result: 'role': 'YAML BRANCH Developer', 'name': 'Bruce Lee', 'message': 'Welcome to YAML developer profile on new_branch'";

		// LAB: "master" branch with BASIC Authentication enabled result but client not authorized:
		//String expected = "/validate result: 'role': 'noRole', 'name': 'Foo Bar', 'message': 'Hello message'";

		// LAB: "master" branch with BASIC Authentication enabled result AND client IS authorized:
		String expected = "/validate result: 'role': 'YAML BRANCH Developer', 'name': 'Bruce Lee', 'message': 'Welcome to YAML developer profile on new_branch'";

		
		
		
		assertThat(result, containsString(expected));

		log.info("*** Successfully pulled configuration for (config-client) through REST ***");

	}
	
	private String createURLWithPort(String uri) {
		return host +":"+ port + uri;
	}

} // The End...
