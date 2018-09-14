package com.baselogic.cloud.configserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Application Tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("trace")
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

}
