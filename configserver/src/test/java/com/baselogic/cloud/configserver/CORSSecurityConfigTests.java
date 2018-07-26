package com.baselogic.cloud.configserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Mick Knutson
 *
 */
@DirtiesContext

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("corsSecurityConfig")
public class CORSSecurityConfigTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mvc;


    //-----------------------------------------------------------------------//

    @Test
    public void test_corsSecurityConfig__client_default_profile() throws Exception {

        MvcResult result = mvc.perform(get("http://localhost:8888/microservices-client/default"))
                .andExpect(status().isOk())

                .andDo(print())
                .andReturn()
                ;

        logger.info("MVC Result: {}", result);
        MockHttpServletResponse response = result.getResponse();
        logger.info("MVC response: {}", response);

        MockHttpServletResponse mockResponse = result.getResponse();

        assertThat(mockResponse.getContentType()).contains("application/json;charset=UTF-8");

        Collection<String> responseHeaders = mockResponse.getHeaderNames();
        assertThat(responseHeaders).isNotNull();
        assertThat(responseHeaders.size()).isBetween(1, 8);

        /*
        Iterator<String> keys = responseHeaders.iterator();


        String key = keys.next();
        assertThat(key).contains("X-Content-Type-Options");
        assertThat(mockResponse.getHeader(key)).contains("nosniff");
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
        assertThat(key).contains("X-Application-Context");
        assertThat(mockResponse.getHeader(key)).contains("application:corsSecurityConfig:-1");
        key = keys.next();
        assertThat(key).contains("Content-Type");
        assertThat(mockResponse.getHeader(key)).contains("application/json;charset=UTF-8");

*/
    }




    //-----------------------------------------------------------------------//


} // The End
