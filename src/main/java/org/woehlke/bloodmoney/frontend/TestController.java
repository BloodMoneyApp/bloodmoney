package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

  /**
   * @return "redirect:/measurement/all"
   */
  @RequestMapping(path = "/createTestData", method = RequestMethod.GET)
  public String createTestData() {
    if (bloodMoneyProperties.getDevTesting()) {
      bloodMoneyTestService.createTestData();
    }
    return "redirect:/measurement/all";
  }
}
