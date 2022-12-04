package org.woehlke.bloodmoney.frontend;


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
import org.woehlke.bloodmoney.frontend.BloodPressureMeasurementControllerExport;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

//TODO #35 Test a Rest Controller with Spring Security
@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class BloodPressureMeasurementEntityExportControllerTest {

    @Autowired
    private BloodPressureMeasurementControllerExport bloodPressureMeasurementControllerExport;

    @Autowired
    private WebApplicationContext context;

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
    public void exportCSVTest(){
            log.info("TEST: exportCSVTest");
    }
}
