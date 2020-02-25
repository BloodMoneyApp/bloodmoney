package org.woehlke.bloodmoney;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodMoneyApplicationTest {

    @Test
    public void contextLoads() {
        System.out.println("TEST: contextLoads");
        Assertions.assertTrue(true);
    }

}
