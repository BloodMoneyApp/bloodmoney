package org.woehlke.bloodmoney.frontend.api;


import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.frontend.model.UserSession;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;
import org.woehlke.bloodmoney.oodm.services.BloodPressureMeasurementService;
import org.woehlke.bloodmoney.user.services.UserSessionService;

@Log
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

    public UserAccountResource(BloodPressureMeasurementService bloodPressureMeasurementService, BloodMoneyProperties bloodMoneyProperties, UserSessionService userSessionService) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.userSessionService = userSessionService;
    }
}
