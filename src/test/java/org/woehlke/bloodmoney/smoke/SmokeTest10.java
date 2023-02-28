package org.woehlke.bloodmoney.smoke;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class SmokeTest10 {

    private final String test = "Test Lombok @Getters";

    @Test
    public void contextLoads() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("TEST: contextLoads");
        assertTrue(true);
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void testLombokGetter() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("TEST: testLombokGetter");
        System.out.println("TEST: testLombokGetter " + this.getTest());
        assertTrue(true);
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void testLombokLogging() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("TEST: testLombokLogging");
        System.out.println("TEST: testLombokLogging " + this.getTest());
        log.info("TEST: testLombokLogging");
        log.info("TEST: testLombokLogging " + this.getTest());
        assertTrue(true);
        log.info("------------------------------------------------------------------------------------------------------");
    }
}
