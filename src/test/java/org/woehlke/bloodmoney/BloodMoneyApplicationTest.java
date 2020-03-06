package org.woehlke.bloodmoney;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class BloodMoneyApplicationTest {

    private String test;

    @Test
    public void contextLoads() {
        System.out.println("TEST: contextLoads");
        Assertions.assertTrue(true);
    }

    @Test
    public void testDocker() {
        System.out.println("TEST: testDocker");
        System.out.println("TEST: testDocker" + this.getTest());
        Assertions.assertTrue(true);
    }

    @Test
    public void testLoggingWithDocker() {
        System.out.println("TEST: testLoggingWithDocker");
        System.out.println("TEST: testLoggingWithDocker" + this.getTest());
        log.info("TEST: testLoggingWithDocker");
        log.info("TEST: testLoggingWithDocker" + this.getTest());
        Assertions.assertTrue(true);
    }
}
