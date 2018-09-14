package com.baselogic.cloud.microservicesclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
public class ServiceEndpointTests {


    @Autowired
    private MockMvc mvc;

    // config.client-com.baselogic.cloud.microservicesclient.MicroservicesClientProperties, microservicesClientProperties
//    @MockBean
//    @Autowired
//    private MicroservicesClientProperties mockProperties;

    @Autowired
    private MicroservicesClientProperties microservicesClientProperties;

//    @MockBean
//    private Credentials credentials;


    @Before
    public void beforeEachTest() {
//        mvc = webAppContextSetup(webApplicationContext).build();

        microservicesClientProperties = microservicesClientProperties();
    }

    //-----------------------------------------------------------------------//

    @Test
    @WithMockUser(username="user2",roles={"USER","ADMIN"})
    public void test_service_endpoint() throws Exception {

//        when(microservicesClientProperties.getProductById(1)).thenReturn(product1);

        mvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }


    private MicroservicesClientProperties microservicesClientProperties(){
        MicroservicesClientProperties properties = new MicroservicesClientProperties();
        properties.setCredentials(
                new Credentials(){{
                    setClients(42);
                    setPassword("changeit");
                    setProvider("FooBar");
                    setUsername("BOB");
                }}
        );

        return properties;
    }


    //-----------------------------------------------------------------------//


} // The End
