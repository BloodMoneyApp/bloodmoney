package org.woehlke.bloodmoney;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Log4j2
@ActiveProfiles("dev")
@SpringBootTest
public class BloodMoneyApplicationTest {

    @Test
    public void contextLoads() {
        System.out.println("TEST: contextLoads");
        log.info("TEST: contextLoads");
        Assertions.assertTrue(true);
    }

}
