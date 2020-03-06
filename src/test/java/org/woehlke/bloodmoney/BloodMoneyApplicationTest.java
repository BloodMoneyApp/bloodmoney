package org.woehlke.bloodmoney;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodMoneyApplicationTest {

    @Test
    public void contextLoads() {
        System.out.println("TEST: contextLoads");
        log.info("TEST: contextLoads");
        Assertions.assertTrue(true);
    }

}
