package org.woehlke.bloodmoney.frontend.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.woehlke.bloodmoney.frontend.model.UserSession;
import org.woehlke.bloodmoney.user.services.UserSessionService;


@Log
@Controller
@SessionAttributes("userSession")
public class HomeController {

    @GetMapping("/")
    public String root(
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        Model model
    ){
        model = userSessionService.handleUserSession(userSession, model);
        return "redirect:/measurement/all";
    }

    @GetMapping("/home")
    public String home(
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        Model model
    ){
        model = userSessionService.handleUserSession(userSession, model);
        return "redirect:/measurement/all";
    }

    private final UserSessionService userSessionService;

    @Autowired
    public HomeController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }
}
