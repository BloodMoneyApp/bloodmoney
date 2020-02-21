package org.woehlke.bloodmoney.frontend;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;
import org.woehlke.bloodmoney.frontend.model.UserSession;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementService;
import org.woehlke.bloodmoney.user.UserSessionService;

import javax.validation.Valid;

/**
 * http://localhost:5000/
 * http://localhost:5000/api/measurement/all
 */
@Slf4j
@RestController
@RequestMapping("/api/measurement")
@SessionAttributes("userSession")
public class BloodPressureMeasurementResource {

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

    @GetMapping("/all")
    public Page<BloodPressureMeasurement> getAll(
        @PageableDefault(sort={"date","time"},direction= Sort.Direction.DESC) Pageable pageable,
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        Page<BloodPressureMeasurement> all = bloodPressureMeasurementService.getAll(pageable);
        return all;
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
