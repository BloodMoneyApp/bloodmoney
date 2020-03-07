package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.woehlke.bloodmoney.application.HomeController;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController controller;

    @Test
    public void contexLoads() throws Exception {
        log.info("TEST: contexLoads");
        assertThat(controller).isNotNull();
    }
}
