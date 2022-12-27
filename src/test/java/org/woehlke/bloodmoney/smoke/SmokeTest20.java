package org.woehlke.bloodmoney.smoke;

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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
    private BloodPressureMeasurementControllerExport bloodPressureMeasurementControllerExport;

    @Autowired
    private MeasurementResource measurementResource;

    @Autowired
    private LoginController loginController;

    @Autowired
    private ErrorController errorController;

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
    public void contexLoads() throws Exception {
        log.info("TEST: contexLoads");
        assertTrue(true);
    }

    @Test
    public void bloodMoneyPropertiesTest()throws Exception {
        log.info("TEST: bloodMoneyPropertiesTest");
        assertThat(bloodMoneyProperties).isNotNull();
    }

    @Test
    public void homeControllerTest() throws Exception {
        log.info("TEST: homeController");
        assertThat(homeController).isNotNull();
    }

    @Test
    public void bloodPressureMeasurementControllerTest() throws Exception {
        log.info("TEST: bloodPressureMeasurementController");
        assertThat(measurementController).isNotNull();
    }

    @Test
    public void bloodPressureMeasurementControllerExportTest() throws Exception {
        log.info("TEST: bloodPressureMeasurementControllerExport");
        assertThat(bloodPressureMeasurementControllerExport).isNotNull();
    }

    @Test
    public void bloodPressureMeasurementResourceTest() throws Exception {
        log.info("TEST: bloodPressureMeasurementResource");
        assertThat(measurementResource).isNotNull();
    }

    @Test
    public void userLoginControllerTest() throws Exception {
        log.info("TEST: userLoginController");
        assertThat(loginController).isNotNull();
    }

    @Test
    public void myErrorControllerTest() throws Exception {
        log.info("TEST: myErrorController");
        assertThat(errorController).isNotNull();
    }
}
