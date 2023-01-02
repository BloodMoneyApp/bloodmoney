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
import org.woehlke.bloodmoney.domain.measurements.MeasurementEntity;
import org.woehlke.bloodmoney.domain.measurements.MeasurementService;
import org.woehlke.bloodmoney.domain.session.UserSessionBean;
import org.woehlke.bloodmoney.domain.session.UserSessionService;

import jakarta.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/measurement")
@SessionAttributes("userSession")
public class MeasurementController {

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

  @RequestMapping(path = "/all", method = RequestMethod.GET)
  public String getAll(
    @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable,
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    model = userSessionService.handleUserSession(userSessionBean, model);
    Page<MeasurementEntity> all = measurementService.getAll(pageable);
    model.addAttribute("all", all);
    return "measurement/all";
  }

  @GetMapping("/{id}")
  public String getOne(
    @PathVariable("id") MeasurementEntity one,
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    model = userSessionService.handleUserSession(userSessionBean, model);
    model.addAttribute("one", one);
    return "measurement/one";
  }

  @GetMapping("/{id}/edit")
  public String editGet(
    @PathVariable("id") MeasurementEntity one,
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    model = userSessionService.handleUserSession(userSessionBean, model);
    model.addAttribute("one", one);
    return "measurement/edit";
  }

  @PostMapping( "/{id}/edit")
  public final String editPost(
    @PathVariable("id") Long id,
    @Valid MeasurementEntity one,
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    BindingResult result, Model model
  ) {
    if (result.hasErrors()) {
      return "measurement/edit";
    } else {
      one = measurementService.update(one, id);
      return "redirect:/measurement/all";
    }
  }

  @GetMapping("/{id}/delete")
  public String deleteGet(
    @PathVariable("id") MeasurementEntity one,
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    measurementService.delete(one);
    return "redirect:/measurement/all";
  }

  @GetMapping(path = "/add")
  public String addGet(
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    model = userSessionService.handleUserSession(userSessionBean, model);
    MeasurementEntity one = MeasurementEntity.getInstance();
    model.addAttribute("one", one);
    return "measurement/add";
  }

  @PostMapping(path = "/add")
  public final String addPost(
    @Valid MeasurementEntity one,
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    BindingResult result, Model model
  ) {
    if (result.hasErrors()) {
      return "measurement/edit";
    } else {
      one = measurementService.add(one);
      return "redirect:/measurement/all";
    }
  }
}
