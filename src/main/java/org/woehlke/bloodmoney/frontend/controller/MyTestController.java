package org.woehlke.bloodmoney.frontend.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;
import org.woehlke.bloodmoney.oodm.services.TestService;

@Log
@Controller
@RequestMapping("/test")
@SessionAttributes("userSession")
public class MyTestController {

    @GetMapping("/createTestData")
    public String createTestData() {
        if(bloodMoneyProperties.getDevTesting()) {
            testService.createTestData();
        }
        return "redirect:/measurement/all";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "test/greeting";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("name", "Thomas");
        return "test/dashboard";
    }

    @GetMapping("/grid")
    public String grid(Model model) {
        model.addAttribute("name", "Thomas");
        return "test/grid";
    }

    @GetMapping("/jumbotron")
    public String jumbotron(Model model) {
        model.addAttribute("name", "Thomas");
        return "test/jumbotron";
    }

    @GetMapping("/navbar-fixed")
    public String navbarFixed(Model model) {
        model.addAttribute("name", "Thomas");
        return "test/navbar-fixed";
    }

    @GetMapping("/sticky-footer-navbar")
    public String stickyFooterNavbar(Model model) {
        model.addAttribute("name", "Thomas");
        return "test/sticky-footer-navbar";
    }

    @Autowired
    public MyTestController(TestService testService, BloodMoneyProperties bloodMoneyProperties) {
        this.testService = testService;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }

    private final TestService testService;
    private final BloodMoneyProperties bloodMoneyProperties;
}
