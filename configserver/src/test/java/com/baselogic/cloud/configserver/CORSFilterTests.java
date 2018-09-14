package com.baselogic.cloud.configserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Mick Knutson
 *
 */
@DirtiesContext

@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

@AutoConfigureMockMvc
@ActiveProfiles("corsFilterBean")
public class CORSFilterTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;


    //-----------------------------------------------------------------------//


    @Test
    public void test_cors__client_default_profile() throws Exception {
        MvcResult result = mvc
                .perform(get(createURIWithPort("/microservices-client/default")))
                .andExpect(status().isOk()) // status 418

                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Csrf-Token, WWW-Authenticate, Authorization"))
                .andExpect(header().string("Access-Control-Expose-Headers", "custom-token1, custom-token2"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "false"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"))

                .andDo(print())
                .andReturn()
                ;

        MockHttpServletResponse mockResponse = result.getResponse();

        assertThat(mockResponse.getContentType()).contains("application/json;charset=UTF-8");

        Collection<String> responseHeaders = mockResponse.getHeaderNames();
        assertThat(responseHeaders).isNotNull();
        assertThat(responseHeaders.size()).isBetween(1, 14);

        /*

        Iterator<String> keys = responseHeaders.iterator();


        String key = keys.next();
//        assertThat(key).contains("X-Content-Type-Options");
        assertThat(key).contains("Access-Control-Allow-Origin");
        assertThat(mockResponse.getHeader(key)).contains("*");
//        assertThat(mockResponse.getHeader(key)).contains("nosniff");

        key = keys.next();
        assertThat(key).contains("X-XSS-Protection");
        assertThat(mockResponse.getHeader(key)).contains("1; mode=block");
        key = keys.next();
        assertThat(key).contains("Cache-Control");
        assertThat(mockResponse.getHeader(key)).contains("no-cache, no-store, max-age=0, must-revalidate");
        key = keys.next();
        assertThat(key).contains("Pragma");
        assertThat(mockResponse.getHeader(key)).contains("no-cache");
        key = keys.next();
        assertThat(key).contains("Expires");
        assertThat(mockResponse.getHeader(key)).contains("0");
//5
        key = keys.next();
        assertThat(key).contains("X-Frame-Options");
        assertThat(mockResponse.getHeader(key)).contains("DENY");
        key = keys.next();
        assertThat(key).contains("Access-Control-Allow-Origin");
        assertThat(mockResponse.getHeader(key)).contains("*");
        key = keys.next();
        assertThat(key).contains("Access-Control-Allow-Methods");
        assertThat(mockResponse.getHeader(key)).contains("POST, GET, PUT, OPTIONS, DELETE");
        key = keys.next();
        assertThat(key).contains("Access-Control-Allow-Headers");
        assertThat(mockResponse.getHeader(key)).contains("Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Csrf-Token, WWW-Authenticate, Authorization");
        key = keys.next();
        assertThat(key).contains("Access-Control-Expose-Headers");
        assertThat(mockResponse.getHeader(key)).contains("custom-token1, custom-token2");
//10
        key = keys.next();
        assertThat(key).contains("Access-Control-Allow-Credentials");
        assertThat(mockResponse.getHeader(key)).contains("false");
        key = keys.next();
        assertThat(key).contains("Access-Control-Max-Age");
        assertThat(mockResponse.getHeader(key)).contains("3600");
        key = keys.next();
        assertThat(key).contains("X-Application-Context");
        assertThat(mockResponse.getHeader(key)).contains("application:corsFilterBean:8888");
        key = keys.next();
        assertThat(key).contains("Content-Type");
        assertThat(mockResponse.getHeader(key)).contains("application/json;charset=UTF-8");
*/
    }


    private URI createURIWithPort(String path) throws URISyntaxException {
        return URI.create("http://localhost:" + port + path);
    }

    //-----------------------------------------------------------------------//


} // The End
