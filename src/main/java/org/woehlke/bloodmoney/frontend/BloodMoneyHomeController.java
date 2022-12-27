package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.woehlke.bloodmoney.domain.session.UserSessionBean;
import org.woehlke.bloodmoney.domain.session.UserSessionService;


@Slf4j
@Controller
@SessionAttributes("userSession")
public class BloodMoneyHomeController {

  private final UserSessionService userSessionService;

  @Autowired
  public BloodMoneyHomeController(UserSessionService userSessionService) {
    this.userSessionService = userSessionService;
  }

  @GetMapping("/")
  public String root(
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    model = userSessionService.handleUserSession(userSessionBean, model);
    return "redirect:/measurement/all";
  }

  @GetMapping("/home")
  public String home(
    @SessionAttribute(name = "userSession", required = false) UserSessionBean userSessionBean,
    Model model
  ) {
    model = userSessionService.handleUserSession(userSessionBean, model);
    return "redirect:/measurement/all";
  }
}
