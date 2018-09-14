package com.baselogic.cloud.configserver;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
@ActiveProfiles("corsWebConfig")
public class CORSWebConfigTests {


    @Autowired
    private MockMvc mvc;


    //-----------------------------------------------------------------------//
    @Test
    public void noop() {
    }

    @Test
    public void test_cors__client_default_profile() throws Exception {
        MvcResult result = mvc
                .perform(get("http://localhost:8888/microservices-client/default"))
                .andExpect(status().isOk())

                /*
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                .allowedHeaders("Origin","X-Requested-With",
                        "Content-Type","Accept","X-Auth-Token","X-Csrf-Token",
                        "WWW-Authenticate","Authorization")
                .exposedHeaders("header1", "header2")

                 */
//                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
//                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
//                .andExpect(header().string("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Csrf-Token, WWW-Authenticate, Authorization"))
//                .andExpect(header().string("Access-Control-Expose-Headers", "custom-token1, custom-token2"))
//                .andExpect(header().string("Access-Control-Allow-Credentials", "false"))
//                .andExpect(header().string("Access-Control-Max-Age", "3600"))

                .andDo(print())
                .andReturn()
                ;

        MockHttpServletResponse mockResponse = result.getResponse();

        assertThat(mockResponse.getContentType()).contains("application/json;charset=UTF-8");

        Collection<String> responseHeaders = mockResponse.getHeaderNames();
        assertThat(responseHeaders).isNotNull();
        assertThat(responseHeaders.size()).isBetween(1, 8);


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
        assertThat(mockResponse.getHeader(key)).contains("application:corsWebConfig:-1");
        key = keys.next();
        assertThat(key).contains("Content-Type");
        assertThat(mockResponse.getHeader(key)).contains("application/json;charset=UTF-8");

    }




    //-----------------------------------------------------------------------//


} // The End
