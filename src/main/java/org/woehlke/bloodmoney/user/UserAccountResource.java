package org.woehlke.bloodmoney.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementService;

@Slf4j
@RestController
@RequestMapping("/api/user")
@SessionAttributes("userSession")
public class UserAccountResource {

    /*
    @GetMapping("/all")
    public Page<BloodPressureMeasurement> getAll(
        @PageableDefault(sort={"date","time"}, direction= Sort.Direction.DESC) Pageable pageable,
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        Page<BloodPressureMeasurement> all = bloodPressureMeasurementService.getAll(pageable);
        return all;
    }
     */

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final BloodMoneyProperties bloodMoneyProperties;
    private final UserSessionService userSessionService;

    @Autowired
    public UserAccountResource(BloodPressureMeasurementService bloodPressureMeasurementService, BloodMoneyProperties bloodMoneyProperties, UserSessionService userSessionService) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.userSessionService = userSessionService;
    }
}
