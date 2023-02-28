package org.woehlke.bloodmoney.frontend;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    private MockMvc mockMvc;

    @PostConstruct
    public void runBeforeAll() {
        log.info("======================================================================================================");
        log.info("TEST: runBeforeAll");
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
        Assertions.assertNotNull(mockMvc, "runBeforeAll() context -> mockMvc");
        log.info("======================================================================================================");
    }

    @PreDestroy
    public void runAfterAll() {
        log.info("======================================================================================================");
        log.info("TEST: runAfterAll");
        log.info("======================================================================================================");
    }

    @Test
    public void bloodMoneyPropertiesTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: bloodMoneyPropertiesTest");
        assertThat(bloodMoneyProperties).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void getRootPublic() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: shouldReturnDefaultMessage: /");
        log.info("------------------------------------------------------------------------------------------------------");
        this.mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().is3xxRedirection());
        log.info("------------------------------------------------------------------------------------------------------");
        log.info("TEST: shouldReturnDefaultMessage: /user/login");
        log.info("------------------------------------------------------------------------------------------------------");
        this.mockMvc.perform(get("/user/login"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Willkommen zu BloodMoney")))
            .andExpect(content().string(containsString("Ihre App um Messwerte zu erfassen")));
        log.info("------------------------------------------------------------------------------------------------------");
    }
}
