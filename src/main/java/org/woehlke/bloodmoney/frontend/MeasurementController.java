package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementEntity;
import org.woehlke.bloodmoney.domain.measurements.MeasurementService;
import org.woehlke.bloodmoney.domain.session.UserSessionBean;
import org.woehlke.bloodmoney.domain.session.UserSessionService;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/measurement")
@SessionAttributes("userSession")
public class MeasurementController {

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getAll(
        @PageableDefault(sort={"created"}, direction=Sort.Direction.DESC) Pageable pageable,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        Page<BloodPressureMeasurementEntity> all = measurementService.getAll(pageable);
        model.addAttribute("all", all);
        return "measurement/all";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getOne(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        model.addAttribute("one", one);
        return "measurement/one";
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.GET)
    public String editGet(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        model.addAttribute("one", one);
        return "measurement/edit";
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.POST)
    public final String editPost(
            @PathVariable("id") Long id,
            @Valid BloodPressureMeasurementEntity one,
            @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
            BindingResult result, Model model
    ) {
        if(result.hasErrors()){
            return "measurement/edit";
        } else {
            one = measurementService.update(one,id);
            return "redirect:/measurement/all";
        }
    }

    @RequestMapping(path = "/{id}/delete", method = RequestMethod.GET)
    public String deleteGet(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        measurementService.delete(one);
        return "redirect:/measurement/all";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addGet(
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ){
        model = userSessionService.handleUserSession(userSessionBean, model);
        BloodPressureMeasurementEntity one = BloodPressureMeasurementEntity.getInstance();
        model.addAttribute("one", one);
        return "measurement/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public final String addPost(
            @Valid BloodPressureMeasurementEntity one,
            @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
            BindingResult result, Model model
    ) {
        if(result.hasErrors()){
            return "measurement/edit";
        } else {
            one = measurementService.add(one);
            return "redirect:/measurement/all";
        }
    }

    private final MeasurementService measurementService;
    private final UserSessionService userSessionService;

    @Autowired
    public MeasurementController(
        MeasurementService measurementService,
        UserSessionService userSessionService
    ) {
        this.measurementService = measurementService;
        this.userSessionService = userSessionService;
    }
}
