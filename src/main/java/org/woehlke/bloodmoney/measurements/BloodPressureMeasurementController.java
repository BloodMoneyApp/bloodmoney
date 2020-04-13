package org.woehlke.bloodmoney.measurements;

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
import org.woehlke.bloodmoney.user.UserSessionBean;
import org.woehlke.bloodmoney.user.UserSessionService;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/measurement")
@SessionAttributes("userSession")
public class BloodPressureMeasurementController {

    @GetMapping("/all")
    public String getAll(
        @PageableDefault(sort={"created"}, direction=Sort.Direction.DESC) Pageable pageable,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        Page<BloodPressureMeasurementEntity> all = bloodPressureMeasurementService.getAll(pageable);
        model.addAttribute("all", all);
        return "measurement/all";
    }

    @GetMapping("/{id}")
    public String getOne(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        model.addAttribute("one", one);
        return "measurement/one";
    }

    @GetMapping("/{id}/edit")
    public String editGet(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        model.addAttribute("one", one);
        return "measurement/edit";
    }

    @PostMapping("/{id}/edit")
    public final String editPost(
            @PathVariable("id") Long id,
            @Valid BloodPressureMeasurementEntity one,
            @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
            BindingResult result, Model model
    ) {
        if(result.hasErrors()){
            return "measurement/edit";
        } else {
            one = bloodPressureMeasurementService.update(one,id);
            return "redirect:/measurement/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteGet(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        bloodPressureMeasurementService.delete(one);
        return "redirect:/measurement/all";
    }

    @GetMapping("/add")
    public String addGet(
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ){
        model = userSessionService.handleUserSession(userSessionBean, model);
        BloodPressureMeasurementEntity one = BloodPressureMeasurementEntity.getInstance();
        model.addAttribute("one", one);
        return "measurement/add";
    }

    @PostMapping("/add")
    public final String addPost(
            @Valid BloodPressureMeasurementEntity one,
            @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
            BindingResult result, Model model
    ) {
        if(result.hasErrors()){
            return "measurement/edit";
        } else {
            one.prepareNew();
            one = bloodPressureMeasurementService.add(one);
            return "redirect:/measurement/all";
        }
    }

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final UserSessionService userSessionService;

    @Autowired
    public BloodPressureMeasurementController(
        BloodPressureMeasurementService bloodPressureMeasurementService,
        UserSessionService userSessionService
    ) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.userSessionService = userSessionService;
    }
}
