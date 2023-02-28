package org.woehlke.bloodmoney.smoke;

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
import org.woehlke.bloodmoney.frontend.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.frontend.MeasurementController;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class SmokeTest20 {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    private HomeController homeController;

    @Autowired
    private MeasurementController measurementController;

    @Autowired
    private MeasurementExportController measurementExportController;

    @Autowired
    private LoginController loginController;

    @Autowired
    private ErrorController errorController;

    @Autowired
    private WebApplicationContext context;

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
    public void contexLoads() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: contexLoads");
        assertTrue(true);
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void bloodMoneyPropertiesTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: bloodMoneyPropertiesTest");
        assertThat(bloodMoneyProperties).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void homeControllerTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: homeController");
        assertThat(homeController).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void bloodPressureMeasurementControllerTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: bloodPressureMeasurementController");
        assertThat(measurementController).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void bloodPressureMeasurementControllerExportTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: bloodPressureMeasurementControllerExport");
        assertThat(measurementExportController).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void userLoginControllerTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: userLoginController");
        assertThat(loginController).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void myErrorControllerTest() throws Exception {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: myErrorController");
        assertThat(errorController).isNotNull();
        log.info("------------------------------------------------------------------------------------------------------");
    }
}
