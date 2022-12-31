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

  private String test = "Test Lombok @Getters";

  @Test
  public void contextLoads() throws Exception {
    System.out.println("TEST: contextLoads");
    assertTrue(true);
  }

  @Test
  public void testLombokGetter() throws Exception {
    System.out.println("TEST: testLombokGetter");
    System.out.println("TEST: testLombokGetter " + this.getTest());
    assertTrue(true);
  }

  @Test
  public void testLombokLogging() throws Exception {
    System.out.println("TEST: testLombokLogging");
    System.out.println("TEST: testLombokLogging " + this.getTest());
    log.info("TEST: testLombokLogging");
    log.info("TEST: testLombokLogging " + this.getTest());
    assertTrue(true);
  }
}
