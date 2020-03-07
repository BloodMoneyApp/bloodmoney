package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.woehlke.bloodmoney.application.HomeController;
import org.woehlke.bloodmoney.application.MyErrorController;
import org.woehlke.bloodmoney.user.login.UserLoginController;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private BloodPressureMeasurementController bloodPressureMeasurementController;

    @Autowired
    private BloodPressureMeasurementControllerExport bloodPressureMeasurementControllerExport;

    @Autowired
    private BloodPressureMeasurementResource bloodPressureMeasurementResource;

    @Autowired
    private UserLoginController userLoginController;

    @Autowired
    private MyErrorController myErrorController;

    @Test
    public void contexLoads() throws Exception {
        log.info("TEST: contexLoads");
        log.info("TEST: homeController");
        assertThat(homeController).isNotNull();
        log.info("TEST: bloodPressureMeasurementController");
        assertThat(bloodPressureMeasurementController).isNotNull();
        log.info("TEST: bloodPressureMeasurementControllerExport");
        assertThat(bloodPressureMeasurementControllerExport).isNotNull();
        log.info("TEST: bloodPressureMeasurementResource");
        assertThat(bloodPressureMeasurementResource).isNotNull();
        log.info("TEST: userLoginController");
        assertThat(userLoginController).isNotNull();
        log.info("TEST: myErrorController");
        assertThat(myErrorController).isNotNull();
    }
}
