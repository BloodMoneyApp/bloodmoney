package org.woehlke.bloodmoney.measurements;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.session.UserSession;
import org.woehlke.bloodmoney.user.session.UserSessionService;

import javax.validation.Valid;

import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.ALL_VALUE;

/**
 * http://localhost:5000/
 * http://localhost:5000/api/rs/1/measurement/all
 */
@Slf4j
@RestController
@RequestMapping(
    path="/api/rs/1/measurement",
    produces={APPLICATION_XML, APPLICATION_JSON_VALUE},
    consumes={ALL_VALUE}
)
@SessionAttributes("userSession")
public class BloodPressureMeasurementResource {

    @GetMapping("/all")
    public Page<BloodPressureMeasurement> getAll(
        @PageableDefault(
            sort={"date","time"},
            direction= Sort.Direction.DESC
        ) Pageable pageable,
        @SessionAttribute(
            name="userSession",
            required=false
        ) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        Page<BloodPressureMeasurement> all = bloodPressureMeasurementService.getAll(pageable);
        return all;
    }

    @GetMapping("/{id}")
    public BloodPressureMeasurement getOne(
        @PathVariable("id") BloodPressureMeasurement one,
        @SessionAttribute(name="userSession",required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        model.addAttribute("one", one);
        return one;
    }

    @PutMapping("/{id}")
    public BloodPressureMeasurement updateOne(
        @PathVariable("id") BloodPressureMeasurement one,
        @SessionAttribute(name="userSession",required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        model.addAttribute("one", one);
        return one;
    }

    @PostMapping("/add")
    public final BloodPressureMeasurement addPost(
        @Valid BloodPressureMeasurement one,
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        BindingResult result, Model model
    ) {
        if(!result.hasErrors()){
            one = bloodPressureMeasurementService.add(one);
        }
        return one;
    }

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final BloodMoneyProperties bloodMoneyProperties;
    private final UserSessionService userSessionService;

    @Autowired
    public BloodPressureMeasurementResource(BloodPressureMeasurementService bloodPressureMeasurementService, BloodMoneyProperties bloodMoneyProperties, UserSessionService userSessionService) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.userSessionService = userSessionService;
    }
}
