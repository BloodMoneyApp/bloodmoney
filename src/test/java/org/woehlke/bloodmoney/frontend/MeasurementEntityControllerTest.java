package org.woehlke.bloodmoney.frontend;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://www.baeldung.com/spring-boot-testing
@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class MeasurementEntityControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @PostConstruct
    public void runBeforeAll() {
        log.info("==================================================================================================");
        log.info("TEST: runBeforeAll");
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
        Assertions.assertNotNull(mockMvc, "runBeforeAll() context -> mockMvc");
        log.info("==================================================================================================");
    }

    @PreDestroy
    public void runAfterAll() {
        log.info("==================================================================================================");
        log.info("TEST: runAfterAll");
        log.info("==================================================================================================");
    }

    @WithMockUser(username = "thomas.woehlke@gmail.com")
    @Test
    public void getMeasurementAllPrivate() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: getMeasurementAllPrivate: /measurement/all");
        this.mockMvc.perform(get("/measurement/all"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<title>Alle Messungen</title>")));
        log.info("--------------------------------------------------------------------------------------------------");
    }
}
