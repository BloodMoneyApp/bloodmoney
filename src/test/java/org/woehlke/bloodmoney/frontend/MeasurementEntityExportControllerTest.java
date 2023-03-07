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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

//TODO #35 Test a Rest Controller with Spring Security
@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class MeasurementEntityExportControllerTest {

    @Autowired
    private MeasurementExportController measurementExportController;

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

    @Test
    public void exportCSVTest() {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: exportCSVTest");
    }
}
