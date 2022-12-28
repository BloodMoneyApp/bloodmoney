package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.domain.test.BloodMoneyTestService;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

@Slf4j
@Controller
@RequestMapping("/test")
@SessionAttributes("userSession")
public class TestController {

  private final BloodMoneyTestService bloodMoneyTestService;
  private final BloodMoneyProperties bloodMoneyProperties;

  @Autowired
  public TestController(
    BloodMoneyTestService bloodMoneyTestService,
    BloodMoneyProperties bloodMoneyProperties
  ) {
    this.bloodMoneyTestService = bloodMoneyTestService;
    this.bloodMoneyProperties = bloodMoneyProperties;
  }

  @RequestMapping(path = "/createTestData", method = RequestMethod.GET)
  public String createTestData() {
    if (bloodMoneyProperties.getDevTesting()) {
      bloodMoneyTestService.createTestData();
    }
    return "redirect:/measurement/all";
  }

  @RequestMapping(path = "/greeting", method = RequestMethod.GET)
  public String greeting(
    @RequestParam(name = "name", required = false, defaultValue = "World") String name,
    Model model
  ) {
    model.addAttribute("name", name);
    return "test/greeting";
  }

  @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
  public String dashboard(Model model) {
    model.addAttribute("name", "Thomas");
    return "test/dashboard";
  }

  @RequestMapping(path = "/grid", method = RequestMethod.GET)
  public String grid(Model model) {
    model.addAttribute("name", "Thomas");
    return "test/grid";
  }

  @RequestMapping(path = "/jumbotron", method = RequestMethod.GET)
  public String jumbotron(Model model) {
    model.addAttribute("name", "Thomas");
    return "test/jumbotron";
  }

  @RequestMapping(path = "/navbar-fixed", method = RequestMethod.GET)
  public String navbarFixed(Model model) {
    model.addAttribute("name", "Thomas");
    return "test/navbar-fixed";
  }

  @RequestMapping(path = "/sticky-footer-navbar", method = RequestMethod.GET)
  public String stickyFooterNavbar(Model model) {
    model.addAttribute("name", "Thomas");
    return "test/sticky-footer-navbar";
  }
}
