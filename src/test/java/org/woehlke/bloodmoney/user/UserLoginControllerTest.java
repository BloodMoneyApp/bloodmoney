package org.woehlke.bloodmoney.user;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class UserLoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    private MockMvc mockMvc;

    @PostConstruct
    public void runBeforeAll() {
        log.info("TEST: runBeforeAll");
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
        Assertions.assertNotNull(mockMvc,"runBeforeAll() context -> mockMvc");
    }

    @PreDestroy
    public void runAfterAll() {
        log.info("TEST: runAfterAll");
    }

    @Test
    public void bloodMoneyPropertiesTest()throws Exception {
        log.info("TEST: bloodMoneyPropertiesTest");
        assertThat(bloodMoneyProperties).isNotNull();
    }

}
