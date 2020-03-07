package org.woehlke.bloodmoney.measurements;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.woehlke.bloodmoney.application.HomeController;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
public class SmokeTest {

    //@Autowired
    private HomeController controller;

   // @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
